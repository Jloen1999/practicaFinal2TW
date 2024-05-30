param (
    [string]$param1,
    [string]$param2
)

# Eliminar el origen remoto existente
git remote remove origin

# Inicializar un nuevo repositorio Git
git init

# Agregar el repositorio remoto utilizando la URL proporcionada
git remote add origin $param1

# Cambiar la rama principal a 'master'
git branch -M master

# Agregar todos los archivos al área de preparación
git add .

# Realizar el commit con el mensaje especificado
git commit -m $param2

# Empujar los cambios al repositorio remoto (origin) en la rama 'master'
git push -u origin master
