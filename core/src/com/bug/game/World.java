package com.bug.game;


import java.util.*;
import static com.badlogic.gdx.Gdx.graphics;

public class World {

    static private int CellCount = 100;  // Количество ячееек в мире (на поле).
    public ArrayList<WorldCell> World = new ArrayList<WorldCell>(); // Масив ячеек мира (поля).
    public ArrayList<Bug> Bugs = new ArrayList<Bug>();  // Масив жуков назодящихся в мире (на поле).
    private Bounds WorldBound;  // Размеры мира.
    float minSide;
    String minSideCell;
   // public WorldCell exemple;
    float w = graphics.getWidth() - 50;
    float h = graphics.getHeight() - 50;


    World () {
       // SetWorldBounds(); // Установка размера игрового поля. Зависимость от размеров экрана.

        GenCells(CellCount);  // Генерация ячеек игрового поля.
        GenBugs("red",11); // Создание персонажей (жуки).
     //   GenBugs("blue",96);


    }

    private void SetWorldBounds() {

        WorldBound = new Bounds();

    }


    /**
     * По расчитаному ID ячейки и ее позиции, создаем экземпляр ячейки и добавляем его в мир.
     */
    private void GenCells(int CellCount) {

        if (w<h){
            minSide = w;

            minSideCell= "x";
        }
        else{
            minSide = h;


            minSideCell= "y";
        }

        float step = (minSide / 10) / 10;
        float cell = (minSide/10) - step;



            if (minSideCell.equals("x")) {

                float startx = 0 + step;
                float starty = (graphics.getWidth() / 2) - (cell*5)- (step*4);
                for (int i = 1; i <= CellCount; i++) {



                    Bounds bound = new Bounds(startx,starty,cell); // Расчет позиции текущей ячейки (в будущем)
                  //  exemple = GenCell("Name" + Integer.toString(i), i, bound);
                    World.add(GenCell("Name" + Integer.toString(i), i, bound));

                    startx+= cell+step;
                    if( i % 10 == 0 && i!=0 ) {
                        starty += cell + step;
                        startx = 0 + step;
                    }

                }

            }
        else if (minSideCell.equals("y")){


                float starty = 0 + step;
                float startx = (graphics.getWidth() / 2) - (cell*5)- (step*4);
                for (int i = 1; i <= CellCount; i++) {

                    Bounds bound = new Bounds(startx,starty,cell); // Расчет позиции текущей ячейки (в будущем)

                    World.add(GenCell("Name" + Integer.toString(i), i, bound));

                    starty+= cell+step;
                    if( i % 10 == 0 && i!=0) {
                        startx += cell + step;
                        starty = 0 + step;
                    }

            }
        }
        else {
                World.add(GenCell("Name" + Integer.toString(1), 1, new Bounds(0, 0, 200)));

            }


    }

    private WorldCell GenCell(String Name, int CellID, Bounds CellBound) {


        return new WorldCell(Name, CellID,CellBound);
    }

    private void GenBugs(String Name, int StartCellID) {

        Bugs.add(new Bug(Name,StartCellID, getCell(StartCellID).GetCellCords()));

    }

    private void GenBugs() {

        Bugs.add(new Bug());

    }

    public WorldCell getCell (int index){
        return World.get(index);
    }

    public Bug getBug (int index){
        return Bugs.get(index);
    }

    public void MakeStep() {

        for (Bug cell:Bugs){
            int position = cell.getCurrentPosition();
            cell.setCurrentPosition(position+1);
        }
    }

}