param (
    [int]$param1,
    [string]$param2
)

# Verificar si se proporcionó un mensaje para el commit
if (-not $param2) {
    Write-Host "Por favor, proporciona un mensaje para el commit."
    exit 1
}

# Verificar la opción proporcionada
if ($param1-eq 0) {
    # Opción 0: Hacer git push
    # Agregar todos los cambios al área de preparación
    git add .

    # Realizar el commit con el mensaje proporcionado como parámetro
    git commit -m $param2

    # Empujar los cambios al repositorio remoto en la rama 'master'
    git push -u origin master
}
elseif ($param1-eq 1) {
    # Opción 1: Hacer git pull
    # Hacer pull de los cambios desde el repositorio remoto en la rama 'master'
    git pull
}
else {
    Write-Host "Opción no válida. Por favor, proporciona 0 para hacer git push o 1 para hacer git pull."
}
