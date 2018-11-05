package main.Serber.pizzeriaExample.cibo;

public class Ingredienti {

    public static final int CIME_DI_RAPA = 0;
    public static final int PATATINE_FRITTE = 1;
    public static final int PORCINI = 2;
    public static final int ZUCCHINE = 3;
    public static final int PEPERONCINO = 4;
    public static final int CIPOLLE = 5;
    public static final int PANCETTA = 6;
    public static final int FRUTTI_DI_MARE = 7;
    public static final int BRIE = 8;
    public static final int AROMI = 9;
    public static final int ACCIUGHE = 10;
    public static final int PEPERONI = 11;
    public static final int MELE = 12;
    public static final int GOGONZOLA = 13;
    public static final int WURSTEL = 14;
    public static final int BASILICO = 15;
    public static final int INSALATA = 16;
    public static final int MOZZARELLA_DI_BUFALA = 17;
    public static final int SCAMORZA = 18;
    public static final int NOCI = 19;
    public static final int FUNGHI_PORCINI = 20;
    public static final int VERDURE_GRIGLIATE = 21;
    public static final int BUFALA = 22;
    public static final int PATATE_LESSE = 23;
    public static final int MELANZANE = 24;
    public static final int CALAMARI_FRITTI = 25;
    public static final int PROSCIUTTO = 26;
    public static final int BRESAOLA = 27;
    public static final int FONTINA = 28;
    public static final int OLIVE = 29;
    public static final int CRUDO = 30;
    public static final int SPECK = 31;
    public static final int PROSCIUTTO_COTTO = 32;
    public static final int POMODORINI = 33;
    public static final int CAPPERI = 34;
    public static final int FRIARIELLI = 35;
    public static final int POLPA_DI_GRANCHIO = 36;
    public static final int CARCIOFI = 37;
    public static final int SALMONE = 38;
    public static final int AGLIO = 39;
    public static final int RUCOLA = 40;
    public static final int MOZZARELLA = 41;
    public static final int OLIVE_NERE = 42;
    public static final int MISTICANZA_DI_VERDURE = 43;
    public static final int ASPARAGI = 44;
    public static final int SALSICCIA = 45;
    public static final int SPINACI = 46;
    public static final int SALAME_PICCANTE = 47;
    public static final int UOVO = 48;
    public static final int TREVISANO = 49;
    public static final int PANNE = 50;
    public static final int OLIVE_VERDI = 51;
    public static final int GORGONZOLA = 52;
    public static final int SALMONE_AFFUMICATO = 53;
    public static final int TONNO = 54;
    public static final int ORIGANO = 55;
    public static final int RICOTTA = 56;
    public static final int PEPE = 57;
    public static final int POMODORO = 58;
    public static final int GRANA = 59;
    public static final int SCAGLIE_DI_GRANA = 60;
    public static final int POMODORINI_FRESCHI = 61;
    public static final int MAIS = 62;
    public static final int COZZE = 63;
    public static final int FUNGHI = 64;
    public static final int GAMBERETTI = 65;

    public static String[] ingriedienti = {
        "Cime di Rapa", "Patatine Fritte", "Porcini", "Zucchine", "Peperoncino",
        "Cipolle", "Pancetta", "Frutti di Mare", "Brie", "Aromi", "Acciughe",
        "Peperoni", "Mele", "Gogonzola", "Wurstel", "Basilico", "Insalata",
        "Mozzarella di Bufala", "Scamorza", "Noci", "Funghi Porcini", "Verdure Grigliate",
        "Bufala", "Patate Lesse", "Melanzane", "Calamari Fritti", "Prosciutto",
        "Bresaola", "Fontina", "Olive", "Crudo", "Speck", "Prosciutto Cotto", "Pomodorini",
        "Capperi", "Friarielli", "Polpa di Granchio", "Carciofi", "Salmone", "Aglio", "Rucola",
        "Mozzarella", "Olive Nere", "Misticanza di Verdure", "Asparagi", "Salsiccia", "Spinaci",
         "Salame Piccante", "Uovo", "Trevisano", "Panne", "Olive Verdi", "Gorgonzola", "Salmone Affumicato",
        "Tonno", "Origano", "Ricotta", "Pepe", "Pomodoro", "Grana", "Scaglie di Grana", "Pomodorini Freschi",
        "Mais", "Cozze", "Funghi", "Gamberetti"};

    public static int valueOf(String string) {
        for (int i = 0; i < ingriedienti.length; i++) {
            if (string.toUpperCase().equals(ingriedienti[i].toUpperCase())) {
                return i;
            }
        }
        return -1;
    }
}
