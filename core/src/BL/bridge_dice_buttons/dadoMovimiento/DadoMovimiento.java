package BL.bridge_dice_buttons.dadoMovimiento;

public class DadoMovimiento implements Cloneable{
    private int movimiento;

    public DadoMovimiento() {
    }

    public int getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(int movimiento) {
        this.movimiento = movimiento;
    }

    @Override
    public DadoMovimiento clone() {
        try {
            return (DadoMovimiento) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
