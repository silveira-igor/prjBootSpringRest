'use strict';

const API_BASE = '';

document.getElementById('loginForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const conta = document.getElementById('conta').value;
    let senha = document.getElementById('senha').value;

    senha = CryptoJS.MD5(senha).toString();

    try {
        const response = await fetch(`${API_BASE}/auth/login`, {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ conta: conta, senhaMD5: senha })
        });

        const data = await response.text();

        if (response.ok) {
            showMessage('Redirecionando...', 'success');
            setTimeout(() => {
                window.location.href = 'dashboard.html';
            }, 2000);
        } else {
            showMessage(data, 'error');
        }
    } catch (error) {
        showMessage('Erro ao conectar com o servidor', 'error');
        console.error('Erro:', error);
    }
});

function showMessage(message, type) {
    const messageDiv = document.getElementById('message');
    messageDiv.textContent = message;
    messageDiv.className = type;
    messageDiv.style.display = 'block';

    setTimeout(() => {
        messageDiv.style.display = 'none';
    }, 5000);
}
