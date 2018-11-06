# Pizzeria Da Gennarino

Consegna : 

Il servizio offerto è un risponditore automatico per poter ordinare alla pizzera Gennarino le pizze ed eventuali vivande. La tecnologia da usare è quella dei Socket TCP in Java. 
- Una classe che realizzi la **macchina a stati finiti**, in pratica un protocollo testuale tipo http, cui corrisponde il sorgente `Pizzeria.java`.
- Una classe su `Server.java` che fa uso del suddetto procollo che lavora in modo concorrente usando gli esecutori Java.
- Il client su di un file `Client.java`.
- Alla fine dell'ordine viene dato un report (cosa hai ordinato) e il prezzo da pagare.
- Facoltativo: i dati e le ordinazioni possono essere mantenuti un un DB: SQLite o MySQL.
- Sulla documentazione aggiungere il file UML per la macchina a stati finiti.

## Client Android e Server per una pizzeria

Tutto come sopra solo che il client è un app Android usando la programmazione concorrente (`Thread`, `Handler` ecc.).

## Pizzeria Da Gennarino (amico di Peppinniello)

La classe Pizzeria non é altro che un oggetto che si presta ad immagazzinare in modo strategico le Pizze , salvandosi per ogni pizza , il proprio prezzo , una lista di fette (da 1 a 12),
e per ogni fette una lista di ingredienti.

Quindi ogni fetta di una pizza contenere ingredienti diversi dalle altre fette adiacenti.

## TCPSerber

Ha la funzione principale di avviare un SerberTask adattandosi alla sua implementazione (UDP-TCP).

## TCPServerTask

Classe che estende SerberTask , dato un ServerSocket , si occupa di gestire ed "accogliere" tutti i clienti che si connettono ad esso.
Gestendo eventuali eccezzioni.

Il TCPSerberTask da il "benvenuto" ai client tramite un HandlerManager.

## HandlerManager 

La classe HandlerManager in realtà si limita ad inglobare la classe PizzeriaManager che é uno dei Manager che può operare nel server e
che dev'essere gestito dall'HandleManager.

In oltre HandlerManager mette a disposizione un Executor che i Manager possono usare per eseguire dei Task...

## PizzeriaManager

E' un Manager pensato per l'esercizio della consegna , all interno contiene un'istanza della classe Pizzeria (singleton).
Tramite l'Executor messo a disposizione dall'HandlerManager , scrive al Client connesso inviandogli la Pizzeria.

Per inviare la Pizzeria al client , la classe PizzeriaManager converte l'istanza della classe Pizzeria in una Stringa con l'ausilio della libreria JSON.

(A livello Client si riutilizza la libreria JSON per ricostruire la Pizzeria)

### Pizzeria 

Una classe che rispetta il pattern Singleton e si limita a restituire , tramite il metodo getListino() la lista contenente tutte le Pizze , presente nella classe MenuPizzeSerber.

## MenuPizzeSerber

E' una classe che viene usata per leggere da file.txt e riempire correttamente la Lista di Pizze.
Offre il listino pizze tramite un metodo statico getListino()

Il file testo é da N righe dove N rappresenta il numero di pizze presenti ed offerte dal menù.
La n-esima riga é composta da 3 campi separati dal carattere ';'

-Il primo campo é il Nome della Pizza
-Il secondo campo é contenuto in due parentesi tonde ( ) , ogni informazione é separata dal carattere ',' , e contiene gli ingredienti della n-esima Pizza.
-Il terzo campo contiene il prezzo , sempre rappresentato con 1 numero decimale , anche se il presso fosse intero

ES : 
    
    Nome        ;(Ingredienti)                 ;Prezzo
    
    Gorgo e Noci;(Mozzarella, Gorgonzola, Noci);7.5


# Client

Il client viene rappresentato in una semplice applicazione Android , scritta in AndroidStudio.

## Pizza

E' una classe che contiene una varietà di informazioni , quali : 
    
- Numero fette desiderate dall utente
- Il nome della pizza
- Il prezzo di quella pizza
- Un Set di ingredienti extra aggiunti
- Un Set di ingredienti rimossi dal prefab
- Una lista di Fette
    

## Ingrediente 

    readme da finire...
