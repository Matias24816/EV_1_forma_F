fun main() {
    val sistema = GymSmart()


    val cardioSocio = Cardio("CA12CD", "Cinta Technogym", TipoUsuario.Socio, "2026-09-08 10:00")
    val cardioVisitante = Cardio("CA99ZA", "Bicicleta Spinning", TipoUsuario.Visitante, "2026-09-08 10:05")
    val fuerzaVisitante = Fuerza("FU22TO", "Press Banca", TipoUsuario.Visitante, "2026-09-08 10:10")
    val funcionalTerceraEdad = Funcional("FN44RG", "TRX Station", TipoUsuario.TerceraEdad, "2026-09-08 10:15", true)
    val funcionalVisitante = Funcional("FN77RG", "CrossFit Rack", TipoUsuario.Visitante, "2026-09-08 10:20", false)
    val codigoInvalido = Cardio("123ABC", "Máquina inválida", TipoUsuario.Visitante, "2026-09-08 10:30")



    println(" Entradas ")
    sistema.registrarEntrada(cardioSocio)
    sistema.registrarEntrada(cardioVisitante)
    sistema.registrarEntrada(fuerzaVisitante)
    sistema.registrarEntrada(funcionalTerceraEdad)
    sistema.registrarEntrada(funcionalVisitante)
    sistema.registrarEntrada(codigoInvalido)


    println("\n Salidas ")
    sistema.registrarSalida("CA12CD", 75)
    sistema.registrarSalida("CA99ZA", 180)
    sistema.registrarSalida("FU22TO", 12)
    sistema.registrarSalida("FN44RG", 120)
    sistema.registrarSalida("FN77RG", 45)


    println("\n Reporte Final ")
    sistema.reporteCierre()
}
