package fitxategiak.eredua;

/**
 * Aktore baten erregistroa irudikatzen duen klasea.
 * Datu-baseko aktore bakoitzaren informazioa gordetzen du.
 */
public class Erregistroa {

	private final int kodea;
	private final String izena;
	private final String jaiotzeData;
	private final String jaiotzeLekua;
	private final String nazionalitatea;
	private final String heriotzaData;
	private final String heriotzaLekua;

	/**
	 * Aktore baten erregistro berria sortzen du.
	 *
	 * @param kodea aktorearen kodea
	 * @param izena aktorearen izena
	 * @param jaiotzeData jaiotze-data
	 * @param jaiotzeLekua jaiotze-lekua
	 * @param nazionalitatea aktorearen nazionalitatea
	 * @param heriotzaData heriotza-data, halakorik badago
	 * @param heriotzaLekua heriotza-lekua, halakorik badago
	 */
	public Erregistroa(
			int kodea,
			String izena,
			String jaiotzeData,
			String jaiotzeLekua,
			String nazionalitatea,
			String heriotzaData,
			String heriotzaLekua) {

		this.kodea = kodea;
		this.izena = izena;
		this.jaiotzeData = jaiotzeData;
		this.jaiotzeLekua = jaiotzeLekua;
		this.nazionalitatea = nazionalitatea;
		this.heriotzaData = heriotzaData;
		this.heriotzaLekua = heriotzaLekua;
	}

	/**
	 * Aktorearen kodea itzultzen du.
	 *
	 * @return aktorearen kodea
	 */
	public int getKodea() {
		return kodea;
	}

	/**
	 * Aktorearen izena itzultzen du.
	 *
	 * @return aktorearen izena
	 */
	public String getIzena() {
		return izena;
	}

	/**
	 * Aktorearen jaiotze-data itzultzen du.
	 *
	 * @return jaiotze-data
	 */
	public String getJaiotzeData() {
		return jaiotzeData;
	}

	/**
	 * Aktorearen jaiotze-lekua itzultzen du.
	 *
	 * @return jaiotze-lekua
	 */
	public String getJaiotzeLekua() {
		return jaiotzeLekua;
	}

	/**
	 * Aktorearen nazionalitatea itzultzen du.
	 *
	 * @return nazionalitatea
	 */
	public String getNazionalitatea() {
		return nazionalitatea;
	}

	/**
	 * Aktorearen heriotza-data itzultzen du.
	 *
	 * @return heriotza-data
	 */
	public String getHeriotzaData() {
		return heriotzaData;
	}

	/**
	 * Aktorearen heriotza-lekua itzultzen du.
	 *
	 * @return heriotza-lekua
	 */
	public String getHeriotzaLekua() {
		return heriotzaLekua;
	}
}