const role = document.querySelector("#role");
const adminCommands = document.querySelector("#for-admin");

const ModalActionClass = class {
    constructor() {
    }

    init() {
        if (role.getAttribute('value') === 'admin') {
            adminCommands.style.display = "";
        } else {
            adminCommands.style.display = "none"
        }
    }
}

myApp = new ModalActionClass;
myApp.init();
