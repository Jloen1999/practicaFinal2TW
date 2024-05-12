function tooltipActive() {
    document.querySelectorAll('[data-bs-toggle="tooltip"]')
        .forEach(tooltip => {
            new bootstrap.Tooltip(tooltip)
        })

}



function formValidation() {
    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    const forms = document.querySelectorAll('.needs-validation');

    // Loop over them and prevent submission
    Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
            if (!form.checkValidity()) {
                event.preventDefault()
                event.stopPropagation()
            }

            form.classList.add('was-validated')
        }, false)
    });
}

function mostrarCamposRegistro() {
    const btnRegistrarse = document.getElementById('registrarse');
    const btnLogin = document.getElementById('login');
    const camposOcultos = document.querySelectorAll('.camposOcultos');
    const offcanvasRightLabel = document.getElementById('offcanvasRightLabel');

    if (btnRegistrarse && camposOcultos && offcanvasRightLabel && btnLogin) {
        btnRegistrarse.onclick = function () {
            camposOcultos.forEach(campo => {
                campo.classList.remove('d-none');
                campo.classList.add('d-block');
            });


            offcanvasRightLabel.innerText = 'Registro';
        }

    }

    btnLogin.onclick = function () {
        camposOcultos.forEach(campo => {
            campo.classList.remove('d-block');
            campo.classList.add('d-none');
        });

        offcanvasRightLabel.innerText = 'Iniciar sesión';
    };
}
