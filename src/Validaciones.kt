fun validarCodigo(codigo: String): Boolean {
    val regex = Regex("^[A-Z]{2}[0-9]{2}[A-Z]{2}$")
    return regex.matches(codigo)
}

