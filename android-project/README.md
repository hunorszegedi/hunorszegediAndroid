# RecipesApp

Egy modern Android alkalmazás, amely lehetővé teszi receptek keresését, hozzáadását és kezelését. Az alkalmazás a Room adatbázist használja a helyi adatok tárolására, valamint tiszta, letisztult felhasználói felületet kínál a receptkezeléshez.

---

## Funkciók
- **Recept hozzáadása:** Könnyedén adhatsz hozzá új recepteket, beleértve a címét, leírását, hozzávalókat és képet.
- **Recept keresése:** Gyors keresés a meglévő receptek között kulcsszó alapján.
- **Saját receptek kezelése:** A felhasználó által hozzáadott receptek listázása és megtekintése.
- **Kategóriák:** Recepteket kategóriák szerint böngészhetsz (pl. Saláta, Desszertek).

---

## Technológiák és eszközök
- **Nyelv:** Kotlin
- **Felhasználói felület:** XML (ConstraintLayout, RecyclerView, FloatingActionButton)
- **Adatbázis:** Room Library (SQLite alapú ORM)
- **Navigáció:** Android Fragmentek és `supportFragmentManager`
- **Képek betöltése:** Glide Library
- **Modern Android komponensek:** Material Design elemek (FloatingActionButton, BottomNavigationView)

---

## Felépítés
Az alkalmazás modulárisan lett kialakítva, a következő fő komponensekkel:
1. **HomeActivity**: Az alkalmazás kezdőlapja, amely népszerű recepteket és kategóriákat listáz.
2. **SearchActivity**: Keresési funkció, amely lehetővé teszi receptek gyors megtalálását.
3. **AddRecipeActivity**: Új receptek hozzáadása egy dedikált felületen keresztül.
4. **ProfileActivity**: Profiloldal, ahol kezelheted saját receptjeidet.
5. **Room adatbázis**:
   - **Dao.kt**: A receptek kezeléséhez szükséges adatbázis műveletek.
   - **Recipe.kt**: Az adatmodell, amely definiálja a recept táblát.

---

## Adatbázis felépítése
A Room adatbázis az alábbi táblát tartalmazza:

| Oszlop   | Típus  | Leírás                           |
|----------|--------|----------------------------------|
| `uid`    | Int    | Egyedi azonosító (autogenerált). |
| `tittle` | String | A recept címe.                   |
| `des`    | String | Recept leírása.                  |
| `ing`    | String | Hozzávalók listája.              |
| `img`    | String | Kép URL-je.                      |
| `category` | String | Kategória (pl. Saláta, Desszert). |


## Használat
### Fejlesztők számára
1. Klónozd a repót:
   ```bash
   git clone https://github.com/hunorszegedi/RecipesApp.git
