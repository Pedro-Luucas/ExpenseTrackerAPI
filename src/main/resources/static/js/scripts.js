// Seleção dos formulários
const loginForm = document.getElementById('loginForm');
const registerForm = document.getElementById('registerForm');

// Validação simples para o formulário de login
if (loginForm) {
    loginForm.addEventListener('submit', function (e) {
        e.preventDefault();
        const email = document.getElementById('loginEmail').value;
        const password = document.getElementById('loginPassword').value;

        if (email && password) {
            alert('Login realizado com sucesso!');
        } else {
            alert('Por favor, preencha todos os campos.');
        }
    });
}

// Validação simples para o formulário de registro
if (registerForm) {
    registerForm.addEventListener('submit', function (e) {
        e.preventDefault();
        const name = document.getElementById('registerName').value;
        const email = document.getElementById('registerEmail').value;
        const password = document.getElementById('registerPassword').value;

        if (name && email && password) {
            alert('Registro realizado com sucesso!');
        } else {
            alert('Por favor, preencha todos os campos.');
        }
    });
}
