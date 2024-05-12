<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!-- Al pulsar en el enlace se abrirá una ventana modal con el juego del ahorcado -->
<li class="nav-item" data-bs-toggle="modal" data-bs-target="#exampleModal">
    <a class="nav-link active" aria-current="page" href="#">Ahorcado</a>
</li>

<!-- Ventana modal para jugar al ahorcado -->
<div class="modal fade text-white" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Jugar al ahorcado</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body" id="ahorcado-container">
                    <h2>Ahorcado</h2>
                    <div id="palabra-container">
                        <!-- Aquí se mostrará la palabra oculta -->
                    </div>
                    <div id="intentos-container">
                        <!-- Aquí se mostrarán los intentos -->
                    </div>
                    <div id="teclado-container">
                        <!-- Aquí se mostrará el teclado virtual -->
                    </div>
            </div>
            <div class="modal-footer d-flex">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <button type="button" id="reiniciar-juego" class="btn btn-primary">Reiniciar Juego</button>
            </div>
        </div>
    </div>
</div>
