package rddfirst;

import java.io.Serializable;

// classı oluşturduktan sonra alt+insert basıp constructor oluştur > alt+insert basıp getter oluştur
public class CupModel implements Serializable {

    String yil;
    String evsahibi;
    String birinci;
    String ikinci;
    String ucuncu;
    String dorduncu;
    int toplamgol;
    int toplamulke;
    int toplammac;
    String toplamkatilimci;

    public CupModel(String yil, String evsahibi, String birinci, String ikinci, String ucuncu, String dorduncu, int toplamgol, int toplamulke, int toplammac, String toplamkatilimci) {
        this.yil = yil;
        this.evsahibi = evsahibi;
        this.birinci = birinci;
        this.ikinci = ikinci;
        this.ucuncu = ucuncu;
        this.dorduncu = dorduncu;
        this.toplamgol = toplamgol;
        this.toplamulke = toplamulke;
        this.toplammac = toplammac;
        this.toplamkatilimci = toplamkatilimci;
    }

    public String getYil() {
        return yil;
    }

    public String getEvsahibi() {
        return evsahibi;
    }

    public String getBirinci() {
        return birinci;
    }

    public String getIkinci() {
        return ikinci;
    }

    public String getUcuncu() {
        return ucuncu;
    }

    public String getDorduncu() {
        return dorduncu;
    }

    public int getToplamgol() {
        return toplamgol;
    }

    public int getToplamulke() {
        return toplamulke;
    }

    public int getToplammac() {
        return toplammac;
    }

    public String getToplamkatilimci() {
        return toplamkatilimci;
    }
}
