package com.bug.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.Gdx.graphics;

public class World {

    static private int CellCount = 100;  // Количество ячееек в мире (на поле).
    // Масив ячеек мира (поля).
    public Map<Integer, WorldCell> World_hm = new HashMap<Integer, WorldCell>();
    public ArrayList<Bug> Bugs = new ArrayList<Bug>();  // Масив жуков назодящихся в мире (на поле).
    private Bounds WorldBound;  // Размеры мира.
    float minSide;
    String minSideCell;
    float w = graphics.getWidth() - 50;
    float h = graphics.getHeight() - 50;
    public Sound sound_step = Gdx.audio.newSound(Gdx.files.internal("sounds/Steps.wav"));

    World () {
       // SetWorldBounds(); // Установка размера игрового поля. Зависимость от размеров экрана.

        GenCells(CellCount);  // Генерация ячеек игрового поля.
        GenBugs("red",0); // Создание персонажей (жуки).
        GenBugs("blue",99);


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
                for (int i = 0; i < CellCount; i++) {



                    Bounds bound = new Bounds(startx,starty,cell); // Расчет позиции текущей ячейки (в будущем)

                    World_hm.put(i, GenCell("Name" + Integer.toString(i), i, bound));
                    startx+= cell+step;
                    if( i % 10 == 9 && i!=0 ) {
                        starty += cell + step;
                        startx = 0 + step;
                    }

                }

            }
        else if (minSideCell.equals("y")){


                float starty = 0 + step;
                float startx = (graphics.getWidth() / 2) - (cell*5)- (step*4);
                for (int i = 0; i < CellCount; i++) {

                    Bounds bound = new Bounds(startx,starty,cell); // Расчет позиции текущей ячейки (в будущем)

                    World_hm.put(i, GenCell("Name" + Integer.toString(i), i, bound));
                    starty+= cell+step;
                    if( i % 10 == 9 && i!=0) {
                        startx += cell + step;
                        starty = 0 + step;
                    }

            }
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
        return World_hm.get(index);
    }

    public Bug getBug (int index){
        return Bugs.get(index);
    }



    public void MakeStep(String key, int ID) {
        if (getBug(ID).cruising_range != 0) {
            getBug(ID).cruising_range--;
            int CurrentPosition = Bugs.get(ID).getCurrentPosition();
            System.out.println("CurrentPosition " + CurrentPosition);
            int LeftRight = CurrentPosition % 10;
            System.out.println("LeftRight " + LeftRight);
            int UpDown = CurrentPosition / 10;
            System.out.println("UpDown " + UpDown);
            int NewPosition;

            if (key == "N") {
                if (UpDown != 9) {
                    NewPosition = Bugs.get(ID).getCurrentPosition() + 10;
                    System.out.println("New positiom " + NewPosition);
                    Bugs.get(ID).setCurrentPosition(NewPosition);
                    Bugs.get(ID).setBound(World_hm.get(NewPosition).GetCellCords());
                    Bugs.get(ID).Sprite.setRotation(270);
                }
            }

            if (key == "S") {
                if (UpDown != 0) {
                    NewPosition = Bugs.get(ID).getCurrentPosition() - 10;
                    System.out.println("New positiom " + NewPosition);
                    Bugs.get(ID).setCurrentPosition(NewPosition);
                    Bugs.get(ID).setBound(World_hm.get(NewPosition).GetCellCords());
                    Bugs.get(ID).Sprite.setRotation(90);
                }
            }
            if (key == "E") {
                if (LeftRight != 0) {
                    NewPosition = Bugs.get(ID).getCurrentPosition() - 1;
                    System.out.println("New positiom " + NewPosition);
                    Bugs.get(ID).setCurrentPosition(NewPosition);
                    Bugs.get(ID).setBound(World_hm.get(NewPosition).GetCellCords());
                    Bugs.get(ID).Sprite.setRotation(180);
                }
            }
            if (key == "W") {
                if (LeftRight != 9) {
                    NewPosition = Bugs.get(ID).getCurrentPosition() + 1;
                    System.out.println("New positiom " + NewPosition);
                    Bugs.get(ID).setCurrentPosition(NewPosition);
                    Bugs.get(ID).setBound(World_hm.get(NewPosition).GetCellCords());
                    Bugs.get(ID).Sprite.setRotation(0);
                }
            }
            sound_step.play();


        }
    }

}