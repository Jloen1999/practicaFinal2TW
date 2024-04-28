<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="es">

    <!-- Incluir head -->
    <%@ include file="head.jsp" %>

<body>
    <!-- Cabecera de la página -->
    <header id="header-container">
        <h1 id="logo">
            <a href="index.html" id="link-logo"><img src="./images/logoUnex.svg" alt="imagen de una biblioteca" id="image-logo"
                    aspect-ratio="1143/760"><span id="text-logo">BiblioCum</span></a>
        </h1>
        <!-- Barra de navegacion -->
        <div class="menu-hamburguesa menu-header">
            <img src="./images/menu-active.svg" id="boton-hamburguesa">
            <nav id="nav-container">
                <ul class="nav-links">
                    <li><a href="libros.html#libro-container">Libros</a></li>
                    <li><a href="novedades.html#novedades-container">Novedades</a></li>
                    <li><a href="reservas.html#reservas-container">Reservas</a></li>
                    <li><a style="text-decoration: underline; text-decoration-color: blue;" href="ahorcado.html#ahorcado-container">Ahorcado</a></li>
                </ul>
            </nav>
        </div>

    </header>

    <!-- Contenido principal -->
    <main>
        <!-- Contenedor de novedades -->
        <div id="novedades-container">
            <section id="novedades-carousel-slide">
                <h2 class="link-h2">Novedades</h2>
                <div id="carousel">
                    <!-- Imágenes de libros -->
                    <a href="#libro1"><img src="./images/libro-elquijote.jpg" alt="Portada de El Quijote"
                            class="imageCarousel" id="elquijote" onmousemove="selectImage('elquijote')"></a>
                    <a href="">
                        <img src="./images/libro-orgulloyprejuicio.png" alt="Portada de Orgullo y Prejuicio"
                            class="imageCarousel" id="orgulloPrejuicio" onmousemove="selectImage('orgulloPrejuicio')"
                            aspect-ratio="16/9">
                    </a>
                    <a href="#libro2"><img src="./images/libro-1984.png" alt="Portada de 1984" class="imageCarousel"
                            id="1984" onmouseover="selectImage('1984')"></a>
                    <a href="#libro3">
                        <img src="./images/libro-El-Señor-de-los-Anillos.png" alt="Portada de El Señor de los Anillos"
                            class="imageCarousel" id="señorAnillos" onmousemove="selectImage('señorAnillos')"
                            aspect-ratio="16/9">
                    </a>
                    <a href="#libro4">
                        <img src="./images/libro-El-Codigo-Da-Vinci.png" alt="Portada de El Código Da Vinci"
                            class="imageCarousel" id="codigoDaVinci" onmouseover="selectImage('codigoDaVinci')"
                            aspect-ratio="16/9">
                    </a>
                    <a href="#libro5"><img src="./images/libro-El-Perfume.png" alt="Portada de El Perfume"
                            class="imageCarousel" id="elPerfume" onmouseover="selectImage('elPerfume')"
                            aspect-ratio="16/9"></a>
                    <a href="#libro6"><img src="./images/libro-El-Hobbit.png" alt="Portada de El Hobbit"
                            class="imageCarousel" id="elHobbit" onmouseover="selectImage('elHobbit')"
                            aspect-ratio="16/9"></a>
                    <a href="#libro7"><img src="./images/libro-El-Alquimista.png" alt="Portada de El Alquimista"
                            class="imageCarousel" id="elAlquimista" onmouseover="selectImage('elAlquimista')"
                            aspect-ratio="16/9"></a>
                    <a href="#libro8"><img src="./images/libro-El-Circulo.png" alt="Portada de El Círculo"
                            class="imageCarousel" id="elCirculo" onmouseover="selectImage('elCirculo')"
                            aspect-ratio="16/9"></a>
                    <a href="#libro9"><img src="./images/libro-El-Silmarillion.png" alt="Portada de El Silmarillion"
                            id="elSilmarillion" onmouseover="selectImage('elSilmarillion')" aspect-ratio="16/9"></a>
                </div>
                <!-- Lista de libros nuevos novedosos -->
                <ul id="nuevos-libros-container">
                    <li id="nuevo-libro1"></li>
                    <li id="nuevo-libro2"></li>
                    <li id="nuevo-libro3"></li>
                    <li id="nuevo-libro4"></li>
                </ul>
            </section>
        </div>

        <!-- Contenedor de libros -->
        <div id="libro-container">
            <h2 class="link-h2">Libros</h2>
            <section id="lista-libros">
                <!-- Artículos de libros -->
                <article class="libro libro1">
                    <h2 class="link-libro">El Quijote</h2>
                    <p>Autor: Miguel de Cervantes</p>
                    <p>Género: Novela</p>
                    <p>Fecha de publicación: 1605</p>
                    <img src="./images/libro-elquijote.jpg" alt="Portada de El Quijote">
                </article>

                <article class="libro libro2">
                    <h2 class="link-libro">Orgullo y Prejuicio</h2>
                    <p>Autor: Jane Austen</p>
                    <p>Género: Novela romántica</p>
                    <p>Fecha de publicación: 1813</p>
                    <img src="./images/libro-orgulloyprejuicio.png" alt="Portada de Orgullo y Prejuicio">
                </article>

                <article class="libro libro3">
                    <h2 class="link-libro">1984</h2>
                    <p>Autor: George Orwell</p>
                    <p>Género: Distopía</p>
                    <p>Fecha de publicación: 1949</p>
                    <img src="./images/libro-1984.png" alt="Portada de 1984">
                </article>

                <article class="libro libro4">
                    <h2 class="link-libro">El Señor de los Anillos</h2>
                    <p>Autor: J.R.R. Tolkien</p>
                    <p>Género: Fantasía</p>
                    <p>Fecha de publicación: 1954</p>
                    <img src="./images/libro-El-Señor-de-los-Anillos.png" alt="Portada de El Señor de los Anillos">
                </article>

                <article class="libro libro5">
                    <h2 class="link-libro">El Código Da Vinci</h2>
                    <p>Autor: Dan Brown</p>
                    <p>Género: Novela de misterio</p>
                    <p>Fecha de publicación: 2003</p>
                    <img src="./images/libro-El-Codigo-Da-Vinci.png" alt="Portada de El Código Da Vinci">
                </article>

                <article class="libro libro6">
                    <h2 class="link-libro">El Perfume</h2>
                    <p>Autor: Patrick Süskind</p>
                    <p>Género: Novela histórica</p>
                    <p>Fecha de publicación: 1985</p>
                    <img src="./images/libro-El-Perfume.png" alt="Portada de El Perfume">
                </article>

                <article class="libro libro7">
                    <h2 class="link-libro">El Hobbit</h2>
                    <p>Autor: J.R.R. Tolkien</p>
                    <p>Género: Fantasía</p>
                    <p>Fecha de publicación: 1937</p>
                    <img src="./images/libro-El-Hobbit.png" alt="Portada de El Hobbit">
                </article>

                <article class="libro libro8">
                    <h2 class="link-libro">El Alquimista</h2>
                    <p>Autor: Paulo Coelho</p>
                    <p>Género: Novela de aventuras</p>
                    <p>Fecha de publicación: 1988</p>
                    <img src="./images/libro-El-Alquimista.png" alt="Portada de El Alquimista">
                </article>

                <article class="libro libro9">
                    <h2 class="link-libro">El Círculo</h2>
                    <p>Autor: Dave Eggers</p>
                    <p>Género: Novela de ciencia ficción</p>
                    <p>Fecha de publicación: 2013</p>
                    <img src="./images/libro-El-Circulo.png" alt="Portada de El Círculo">
                </article>

                <article class="libro libro10">
                    <h2 class="link-libro">El Silmarillion</h2>
                    <p>Autor: J.R.R. Tolkien</p>
                    <p>Género: Fantasía</p>
                    <p>Fecha de publicación: 1977</p>
                    <img src="./images/libro-El-Silmarillion.png" alt="Portada de El Silmarillion">
                </article>
            </section>
        </div>

        <!-- Contenedor de reservas -->
        <section id="reserva-container">
            <h2>Haz tu reserva</h2>
            <form id="formulario" onsubmit="return validarFormulario()">
                <!-- Campos del formulario -->
                <div class="section-content" style="align-items: center;">
                    <label for="username" class="label-name"><span class="span-label">Nombre:</span></label>
                    <div>
                        <input type="text" id="username" value="" size="30" placeholder="Introduce un nombre"/>
                        <p style="font-size: 80%" class="span-nota"><span class="primera-letra">*</span>Nota: Mínimo
                            3 caracteres!</span>
                    </div>
                </div>

                <div class="section-content" style="align-items: center;">
                    <label class="label-name"><span class="span-label">Email:</span></label>
                    <div>
                        <input type="text" id="email" value="" size="30" placeholder="Introduce un email" />
                        <p style="font-size: 80%" class="span-nota"><span class="primera-letra">*</span>Nota: Debe
                            ser correcto!</p>
                    </div>
                </div>

                <div class="section-content" style="align-items: center;">
                    <label class="label-name"><span class="span-label">Teléfono:</span></label>
                    <div>
                        <input type="text" id="telefono" value="" size="30" placeholder="Introduce un telefono"/>
                        <p style="font-size: 80%" class="span-nota"><span class="primera-letra">*</span>Nota: Debe
                            ser xxx-xxx-xxx!</p>
                    </div>
                </div>

                <div class="section-content">
                    <label for="fechaReserva" class="label-name"><span class="span-label">Fecha de
                            reserva:</span></label>
                    <input type="date" id="fechaReserva" value="" />
                </div>
                <div class="section-content">
                    <label for="fechaDevolucion" class="label-name"><span class="span-label">Fecha de
                            devolución:</span></label>
                    <input type="date" id="fechaDevolucion" value="" />
                </div>

                <div class="section-content">
                    <label for="biblioteca" class="label-name"><span class="span-label">Biblioteca:</span></label>
                    <ul style="margin-top: 0;" class="lista-biblioteca">
                        <li class="biblio">
                            <input type="radio" id="biblio-merida" name="education" value=""
                                onclick="subrayarElemento('biblio-merida')" />
                            <label for="biblio-merida" class="label-item">Biblioteca de Mérida</label>
                        </li>
                        <li class="biblio">
                            <input type="radio" id="biblio-caceres" name="education" value=""
                                onclick="subrayarElemento('biblio-caceres')" />
                            <label for="biblio-caceres" class="label-item">Biblioteca de Cáceres</label>
                        </li>
                        <li class="biblio">
                            <input type="radio" id="biblio-placencia" name="education" value=""
                                onclick="subrayarElemento('biblio-placencia')" />
                            <label for="biblio-placencia" class="label-item">Biblioteca de Plasencia</label>
                        </li>
                        <li class="biblio">
                            <input type="radio" id="biblio-badajoz" name="education" value=""
                                onclick="subrayarElemento('biblio-badajoz')" />
                            <label for="biblio-badajoz" class="label-item">Biblioteca de Badajoz</label>
                        </li>
                    </ul>

                </div>

                <div class="section-content">
                    <label for="libro-select" class="label-name"><span class="span-label">Libro:</span></label>
                    <div>
                        <select id="libro-select" name="libro">
                            <option value="">Selecciona un libro</option>
                            <hr>
                            <option value="El Quijote">El Quijote</option>
                            <hr>
                            <option value="Orgullo y Prejuicio">Orgullo y Prejuicio</option>
                            <hr>
                            <option value="1984">1984</option>
                            <hr>
                            <option value="El Señor de los Anillos">El Señor de los Anillos</option>
                            <hr>
                            <option value="El Código Da Vinci">El Código Da Vinci</option>
                            <hr>
                            <option value="El Perfume">El Perfume</option>
                            <hr>
                            <option value="El Hobbit">El Hobbit</option>
                            <hr>
                            <option value="El Alquimista">El Alquimista</option>
                            <hr>
                            <option value="El Círculo">El Círculo</option>
                            <hr>
                            <option value="El Silmarillion">El Silmarillion</option>
                        </select>
                    </div>
                </div>

                <div class="section-content">
                    <label class="label-name"><span class="span-label-info">Información
                            adicional:</span></label>
                    <textarea id="more" rows="10px" cols="39px"></textarea>
                </div>

                <footer id="footerFormulario">
                    <input type="submit" value="Reservar" style="margin-left: 150px;">
                    <input type="reset" value="Limpiar Formulario">
                    <div id="mis-reservas">
                        <button id="mostrar-reservas">Mostrar reservas</button>
                        <ul id="lista-reservas">
                            <!-- Aquí se mostrarán las reservas del usuario -->
                        </ul>
                    </div>
                </footer>
            </form>
        </section>

        <!-- Contenedor del juego de ahorcado -->
        <section id="ahorcado-container">
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
            <button id="reiniciar-juego">Reiniciar Juego</button>
        </section>

    </main>

    <!-- Pie de página -->
    <footer id="footerFinal">
        <p>&copy; 2024 BiblioCum - Jose Luis Obiang Ela Nanguang</p>
    </footer>

    <%@ include file="scriptsJS.jsp" %>
</body>

</html>