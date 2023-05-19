document.getElementById('addTask').addEventListener('click', async () => {
        const input = document.getElementById('taskText');
        const title = input.value;

        if (title) {
            const res = await fetch('http://localhost:8081/tasks/addTask', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({title, completed: false})
            });

            const task = await res.json();
            taskToHTML(task);

            input.value= '';
        }
    }
)
async function getAllTasks() {
    const res = await fetch('http://localhost:8081/tasks/getAllTasks');
    const tasks = await res.json();

    console.log(tasks);
    tasks.forEach(task => taskToHTML(task))
}

window.addEventListener('DOMContentLoaded', getAllTasks);

function taskToHTML({id, completed, title}) {
const taskList = document.getElementById('tasks');
taskList.insertAdjacentHTML('beforeend',
    `    <div class="form-check" id="task${id}">
<label class="form-check-label">
    <input onchange="update(${id})" type="checkbox" class="form-check-input" ${completed && 'checked'}>
    ${title}
    </label>
        <button onclick="deleteTask(${id})" type="button" class="btn-close"
                aria-label="Close" style="font-size: 10px"></button>
    </div>`
);
}

async function deleteTask(id) {
    const res = await fetch(` http://localhost:8081/tasks/deleteTask/${id}`, {
        method: 'DELETE',
        headers: {
        'Content-Type': 'application/json'
        }
    });

    const data = await res.json();
    console.log(data);

    if (data) {
        document.getElementById(`task${id}`).remove();
    }
}

async function update(id) {
    const completed = document.querySelector(`#task${id} input`).checked;

    const res = await fetch(` http://localhost:8081/tasks/update/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({completed})
    })
    const data = await res.json();
    console.log(data);
}
