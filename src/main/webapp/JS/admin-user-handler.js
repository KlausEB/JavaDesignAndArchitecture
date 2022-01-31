const role = document.querySelector("#role");
const adminCommands = document.querySelector("#for-admin");

function init() {
    if (role.getAttribute('value') === 'admin') {
        adminCommands.style.display = "";
    } else {
        adminCommands.style.display = "none"
    }
}

init();
