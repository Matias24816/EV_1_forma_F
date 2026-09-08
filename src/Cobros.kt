fun aplicarReglasFinales(base: Double, usuario: TipoUsuario): Double {
    var monto = base * 1.19 // IVA
    if (usuario == TipoUsuario.TerceraEdad) monto *= 0.5

    if (monto <= 0) {
        println("Error: tarifa inválida (monto calculado = $monto)")
        return 0.0
    }

    return monto
}
