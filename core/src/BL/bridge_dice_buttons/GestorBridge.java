package BL.bridge_dice_buttons;

import BL.bridge_dice_buttons.botones.BtnAccion;
import BL.bridge_dice_buttons.botones.BtnInvocacion;
import BL.bridge_dice_buttons.dadoMovimiento.DadoMovimiento;
import BL.bridge_dice_buttons.estadosInvocacion.Accion;
import BL.bridge_dice_buttons.estadosInvocacion.*;

import java.util.*;

public class GestorBridge {
    private BtnInvocacion btnInvocacionInfanteria = new BtnInvocacion("infanteria");
    private BtnInvocacion btnInvocacionTanque = new BtnInvocacion("tanque");
    private BtnInvocacion btnInvocacionArtilleria = new BtnInvocacion("artilleria");
    private BtnAccion btnAtaque = new BtnAccion("Ataque");
    private BtnAccion btnAtaqueEspecial = new BtnAccion("AtaqueEspecial");
    private BtnAccion btnMovimiento = new BtnAccion("Movimiento");

    private int dadosInfanteriaVolatil = 0;
    private int dadosInfanteriaTotal = 0;

    private int dadosArtilleriaVolatil = 0;
    private int dadosArtilleriaTotal = 0;

    private int dadosTanqueVolatil = 0;
    private int dadosTanqueTotal = 0;

    private ArrayList<DadoMovimiento> dadosMovimiento = new ArrayList<DadoMovimiento>();
    private int cantDadosMovimiento = 0;
    private int dadosAtaqueEspecial = 0;
    private int dadosAtaque = 0;
    Scanner scanner = new Scanner(System.in);

    private static final Map<Integer, String> TIPOS_DE_INVOCACION = new HashMap<Integer, String>() {{
        put(1, "infanteria");
        put(2, "artilleria");
        put(3, "infanteria");
        put(4, "artilleria");
        put(5, "infanteria");
        put(6, "tanque");
    }};

    private static final Map<Integer, String> TIPOS_DE_ACCION = new HashMap<Integer, String>() {{
        put(1, "move");
        put(2, "attack");
        put(3, "sattack");
        put(4, "move");
        put(5, "attack");
        put(6, "sattack");
    }};

    public GestorBridge() { }

    public ArrayList<Integer> lanzarDado() {
        ArrayList<Integer> dadosVolatiles = new ArrayList<Integer>();

        dadosInfanteriaVolatil = 0;
        dadosArtilleriaVolatil = 0;
        dadosTanqueVolatil = 0;
        dadosAtaque = 0;
        dadosAtaqueEspecial = 0;

        Random random = new Random();

        for (int i = 0; i < 2; i++){
            int numRandom = random.nextInt(6) + 1;

            if (TIPOS_DE_INVOCACION.getOrDefault(numRandom, null).equals("infanteria")) {

                dadosInfanteriaVolatil++;

            } else if (TIPOS_DE_INVOCACION.getOrDefault(numRandom, null).equals("artilleria")) {

                dadosArtilleriaVolatil++;

            } else if (TIPOS_DE_INVOCACION.getOrDefault(numRandom, null).equals("tanque")) {

                dadosTanqueVolatil++;

            }
        }

        int numRandom = random.nextInt(6) + 1;

        if (TIPOS_DE_ACCION.getOrDefault(numRandom, null).equals("move")) {

            DadoMovimiento dadoMovimiento = new DadoMovimiento();
            dadoMovimiento.setMovimiento(random.nextInt(6) + 1);

            System.out.println(dadoMovimiento.getMovimiento());

            dadosMovimiento.add(dadoMovimiento);
            cantDadosMovimiento = dadosMovimiento.size();


        } else if (TIPOS_DE_ACCION.getOrDefault(numRandom, null).equals("attack")) {
            dadosAtaque++;

        } else if (TIPOS_DE_ACCION.getOrDefault(numRandom, null).equals("sattack")) {
            dadosAtaqueEspecial++;
        }

        dadosVolatiles.add(0, dadosInfanteriaVolatil);
        dadosVolatiles.add(1, dadosArtilleriaVolatil);
        dadosVolatiles.add(2, dadosTanqueVolatil);
        dadosVolatiles.add(3, dadosAtaque);
        dadosVolatiles.add(4, dadosAtaqueEspecial);

        return dadosVolatiles;
    }

    public ArrayList<Integer> almacenarCofre(){
        ArrayList<Integer> dados = new ArrayList<Integer>();

        dados.add(0, dadosInfanteriaVolatil);
        dados.add(1, dadosArtilleriaVolatil);
        dados.add(2, dadosTanqueVolatil);
        dados.add(3, dadosAtaque);
        dados.add(4, dadosAtaqueEspecial);
        dados.add(5, 0);

        dadosInfanteriaVolatil = 0;
        dadosArtilleriaVolatil = 0;
        dadosTanqueVolatil = 0;
        dadosAtaque = 0;
        dadosAtaqueEspecial = 0;

        return dados;
    }

    public String invocarInfanteria(int infanteriasCofre){
        dadosInfanteriaTotal = btnInvocacionInfanteria.getGlobalValidacion().validar(dadosInfanteriaVolatil + infanteriasCofre) ;
        return btnInvocacionInfanteria.onPressed(btnInvocacionInfanteria.getGlobalValidacion().getEstado());
    }

    public int evaluarCofreInfanteria(int infanteriasCofre){
        if (dadosInfanteriaVolatil == 1){
            dadosInfanteriaVolatil = 0;
            infanteriasCofre -= 1;
        } else if (dadosInfanteriaVolatil == 0){
            infanteriasCofre -= 2;
        }else if (dadosInfanteriaVolatil == 2){
            dadosInfanteriaVolatil = 0;
        }
        return infanteriasCofre;
    }

    public String invocarArtilleria(int artilleriasCofre){
        dadosArtilleriaTotal = btnInvocacionArtilleria.getGlobalValidacion().validar(dadosArtilleriaVolatil + artilleriasCofre);
        return btnInvocacionArtilleria.onPressed(btnInvocacionArtilleria.getGlobalValidacion().getEstado());
    }

    public int evaluarCofreArtilleria(int artilleriasCofre){
        if (dadosArtilleriaVolatil == 1){
            dadosArtilleriaVolatil = 0;
            artilleriasCofre -= 2;
        } else if (dadosArtilleriaVolatil == 0){
            artilleriasCofre -= 3;
        } else if (dadosArtilleriaVolatil == 2){
            dadosArtilleriaVolatil = 0;
            artilleriasCofre -= 1;
        }
        return artilleriasCofre;
    }

    public String invocarTanque(int tanquesCofre){
        dadosTanqueTotal = btnInvocacionTanque.getGlobalValidacion().validar(dadosTanqueVolatil + tanquesCofre);
        return btnInvocacionTanque.onPressed(btnInvocacionTanque.getGlobalValidacion().getEstado());
    }

    public int evaluarCofreTanque(int tanquesCofre){
        if (dadosTanqueVolatil == 1){
            dadosTanqueVolatil = 0;
            tanquesCofre -= 3;
        } else if (dadosTanqueVolatil == 0){
            tanquesCofre -= 4;
        } else if (dadosTanqueVolatil == 2){
            dadosTanqueVolatil = 0;
            tanquesCofre -= 2;
        }
        return tanquesCofre;
    }

    public String atacar(){
        dadosAtaque = btnAtaque.getGlobalValidacion().validar(dadosAtaque);
        return btnAtaque.onPressed(btnAtaque.getGlobalValidacion().getEstado());
    }

    public String ejecutarAtaqueEspecial(){
        dadosAtaqueEspecial = btnAtaqueEspecial.getGlobalValidacion().validar(dadosAtaqueEspecial);
        return btnAtaqueEspecial.onPressed(btnAtaqueEspecial.getGlobalValidacion().getEstado());
    }

    public String mover(){
        cantDadosMovimiento = btnMovimiento.getGlobalValidacion().validar(cantDadosMovimiento);
        if(btnMovimiento.getGlobalValidacion().getEstado())
        {
            return seleccionarMovimiento();
        } else {
            return "No tiene suficientes dados para moverse";
        }
    }

    private String seleccionarMovimiento(){
        for (DadoMovimiento dadoMovimiento : dadosMovimiento)
        {
            int i = 0;
            i++;
            System.out.println("- Dado: " + i + " con movimiento: " + dadoMovimiento.getMovimiento() + "\n");
        }
        System.out.println("Seleccione el dado que desea usar: ");
        int dadoSeleccionado = scanner.nextInt();
        String msg = "Mover: " + dadosMovimiento.get(dadoSeleccionado).getMovimiento();
        System.out.println(msg);
        dadosMovimiento.remove(dadoSeleccionado);
        return msg;
    }

    private void cambiarEstadoBotones(String tipoBtn) {
        tipoBtn = tipoBtn.toLowerCase();

        if (tipoBtn.equals("infanteria")) {
            btnInvocacionInfanteria.setGlobalValidacion(new InvocacionInfanteriaEstado());
        } else if (tipoBtn.equals("artilleria")) {
            btnInvocacionArtilleria.setGlobalValidacion(new InvocacionArtilleriaEstado());
        } else if (tipoBtn.equals("tanque")) {
            btnInvocacionTanque.setGlobalValidacion(new InvocacionTanqueEstado());
        } else if (tipoBtn.equals("ataque")) {
            btnAtaque.setGlobalValidacion(new Accion());
        } else if (tipoBtn.equals("ataqueespecial")) {
            btnAtaqueEspecial.setGlobalValidacion(new Accion());
        } else if (tipoBtn.equals("movimiento")) {
            btnMovimiento.setGlobalValidacion(new Accion());
        }
    }

    public void iniciarBotones(){
        cambiarEstadoBotones("infanteria");
        cambiarEstadoBotones("artilleria");
        cambiarEstadoBotones("tanque");
        cambiarEstadoBotones("ataque");
        cambiarEstadoBotones("ataqueEspecial");
        cambiarEstadoBotones("movimiento");
    }
}