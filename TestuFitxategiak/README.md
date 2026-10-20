# TestuFitxategiak — 2AG3 (Datu Atzipena)

Eclipseko Java proiektua, **Ariketa - FileReader Class** egiteko.

## Proiektua

- `fitxategiak.aplikazioa.Main`: programa abiarazi eta Scanner bakarra kudeatu.
- `fitxategiak.aplikazioa.Menua`: teklatuko sarrerak, balidazioak eta mezuak.
- `fitxategiak.eredua.Ikaslea`: `kodea;izena;herria;aktiboa` erregistroa.
- `fitxategiak.datuak.IkasleDatuak`: bost adibideko ikasle fikziozko; 1. aukeran soilik.
- `fitxategiak.kudeaketa.TestuFitxategiKudeatzailea`: fitxategiaren gaineko eragiketak.

## Formatua

Lehen lerroa: `2AG3 1.0`. Ondorengo lerro bakoitza: `kodea;izena;herria;aktiboa`.

`2AG3.txt` fitxategia ez da aurrez sortu behar: programa hastean izena eskatu eta sortzeko baieztapena eskatzen du. `1` aukerak adibideko ikasleak idazten ditu.

## Menua

1. Hasierako datuekin bete
2. Fitxategia hustu (goiburua mantenduta)
3. Erregistroak zenbatu
4. Kodearen arabera bilatu
5. Hitzaren arabera bilatu
6. Erregistro berria gehitu (kode bakarra)
7. Erregistroa ezabatu (fitxategi laguntzailearekin)
8. Fitxategiaren kopia sortu (gainidatzi gabe)
9. Erregistroa aldatu (fitxategi laguntzailearekin)
0. Irten

Fitxategi-erregistroak `BufferedReader` bidez lerroz lerro prozesatzen dira; ez da `ArrayList` erabiltzen datuak lanerako kargatzeko. Fitxategiak `try-with-resources` erabiliz ixten dira.

## Eclipse

`File -> Import -> General -> Existing Projects into Workspace` eta hautatu `TestuFitxategiak` karpeta. Exekutatu `fitxategiak.aplikazioa.Main`.

**Kontuz:** 1, 2, 7 eta 9 aukerek hautatutako fitxategiaren edukia aldatzen dute. Probak egiteko, erabili berariazko proba-fitxategia.
