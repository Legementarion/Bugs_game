package com.bug.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.badlogic.gdx.Gdx.graphics;

public class World {

    static private int CellCount = 100;  // Количество ячееек в мире (на поле).
    // Масив ячеек мира (поля).
    public Map<Integer, WorldCell> WorldMap = new HashMap<Integer, WorldCell>();
    public ArrayList<Bug> Bugs = new ArrayList<Bug>();  // Масив жуков назодящихся в мире (на поле).
    private Bounds WorldBound;  // Размеры мира.
    public Bug SelectedBug;
    float minSide;
    String minSideCell;
    float w = graphics.getWidth() - 50;
    float h = graphics.getHeight() - 50;
    public Sound StepSound = Gdx.audio.newSound(Gdx.files.internal("sounds/Steps.wav"));

    World() {
        // SetWorldBounds(); // Установка размера игрового поля. Зависимость от размеров экрана.

        GenCells(CellCount);  // Генерация ячеек игрового поля.
        GenBugs(1,7);


    }


    private void SetWorldBounds() {

        WorldBound = new Bounds();

    }


    /**
     * По расчитаному ID ячейки и ее позиции, создаем экземпляр ячейки и добавляем его в мир.
     */
    private void GenCells(int CellCount) {

        if (w < h) {
            minSide = w;

            minSideCell = "x";
        } else {
            minSide = h;


            minSideCell = "y";
        }

        float step = 0;//(minSide / 10) / 10;
        float cell = (minSide / 10);


        if (minSideCell.equals("x")) {

            float startx = 0 + step;
            float starty = (graphics.getWidth() / 2) - (cell * 5) - (step * 4);
            for (int i = 0; i < CellCount; i++) {


                Bounds bound = new Bounds(startx, starty, cell); // Расчет позиции текущей ячейки (в будущем)

                WorldMap.put(i, GenCell("1", i, bound));
                startx += cell;
                if (i % 10 == 9 && i != 0) {
                    starty += cell;
                    startx = 0;
                }

            }

        } else if (minSideCell.equals("y")) {


            float starty = 0 + step;
            float startx = (graphics.getWidth() / 2) - (cell * 5) - (step * 4);
            for (int i = 0; i < CellCount; i++) {

                Bounds bound = new Bounds(startx, starty, cell); // Расчет позиции текущей ячейки (в будущем)

                WorldMap.put(i, GenCell("1", i, bound));
                starty += cell;
                if (i % 10 == 9 && i != 0) {
                    startx += cell;
                    starty = 0 ;
                }

            }
        }

    }

    private WorldCell GenCell(String Name, int CellID, Bounds CellBound) {
        return new WorldCell(Name, CellID, CellBound);
    }

    private void GenBugs(String Name, int StartCellID) {

        Bugs.add(new Bug(Name, StartCellID, getCell(StartCellID).GetCellCords(), Bugs.size()));

    }

    private void GenBugs(int BugsNum, int TeamNum) {
        String [] TeamsColorPattern = {"red","blue","orange","yellow","green","indigo","violet"};
        ArrayList <Integer> ExistingID = new ArrayList<Integer>();


        for (int Team=0; Team<TeamNum; Team++) {
            for (int Bug = 0; Bug < BugsNum; Bug++) {
                int StartCellID = GenCellID(ExistingID);
                Gdx.app.log("GenBugID", "BugID: " + StartCellID);
                ExistingID.add(StartCellID);
                Bugs.add(new Bug(TeamsColorPattern[Team], StartCellID, getCell(StartCellID).GetCellCords(), Bugs.size()));
                WorldMap.get(StartCellID).setName(TeamsColorPattern[Team]);
            }

        }
    }

    private int GenCellID (ArrayList <Integer> ExistingID) {

        do {
            int CellsStatus = 0;
            int CellID = new Random().nextInt(CellCount);
            for (int i:ExistingID) { if(CellID == i) {CellsStatus++;} }
            if (CellsStatus == 0) {return CellID; }
        }
        while (true);

    }

    public WorldCell getCell(int index) {
        return WorldMap.get(index);
    }

    public Bug getBug(int index) {
        return Bugs.get(index);
    }




    public void MakeStep (WorldCell Cell) {

        if (Cell.getCellID() == Bugs.get(SelectedBug.getID()).getCurrentPosition()+1 ||
            Cell.getCellID() == Bugs.get(SelectedBug.getID()).getCurrentPosition()-1 ||
            Cell.getCellID() == Bugs.get(SelectedBug.getID()).getCurrentPosition()+10 ||
            Cell.getCellID() == Bugs.get(SelectedBug.getID()).getCurrentPosition()-10)
        {
            Gdx.app.log("Step: ", "Going from " + SelectedBug.getCurrentPosition() + " to " + Cell.getCellID() + "!");
            UnSelectPossibleSteps(SelectedBug);

            if(Cell.getCellID() == Bugs.get(SelectedBug.getID()).getCurrentPosition()-1) {
                Gdx.app.log("Before Rotation: ", "X: " +  Bugs.get(SelectedBug.getID()).Sprite.getX() + "|Y: "
                        + Bugs.get(SelectedBug.getID()).Sprite.getY() + "|Size: "
                        + Bugs.get(SelectedBug.getID()).Sprite.getHeight());

                Bugs.get(SelectedBug.getID()).Sprite.setOriginCenter();
                Bugs.get(SelectedBug.getID()).Sprite.setRotation(180);
                Gdx.app.log("After Rotation: ", "X: " + Bugs.get(SelectedBug.getID()).Sprite.getX() + "|Y: "
                        + Bugs.get(SelectedBug.getID()).Sprite.getY() + "|Size: "
                        + Bugs.get(SelectedBug.getID()).Sprite.getHeight());

                }
            if(Cell.getCellID() == Bugs.get(SelectedBug.getID()).getCurrentPosition()-10){
                Bugs.get(SelectedBug.getID()).Sprite.setOriginCenter();
                Bugs.get(SelectedBug.getID()).Sprite.setRotation(90);
                }
            if(Cell.getCellID() == Bugs.get(SelectedBug.getID()).getCurrentPosition()+10){
                Bugs.get(SelectedBug.getID()).Sprite.setOriginCenter();
                Bugs.get(SelectedBug.getID()).Sprite.setRotation(270);
                }
            if(Cell.getCellID() == Bugs.get(SelectedBug.getID()).getCurrentPosition()+1){
                Bugs.get(SelectedBug.getID()).Sprite.setOriginCenter();
                Bugs.get(SelectedBug.getID()).Sprite.setRotation(0);
                }
            Bugs.get(SelectedBug.getID()).setCurrentPosition(Cell.getCellID());
            Bugs.get(SelectedBug.getID()).setBound(WorldMap.get(Cell.getCellID()).GetCellCords());
            WorldMap.get(Cell.getCellID()).setName(Bugs.get(SelectedBug.getID()).getName());
            SelectPossibleSteps(SelectedBug);
           // Bugs.get(SelectedBug.getID()).CruisingRange--;

            StepSound.play();


        }

    }

    public boolean CheckNextStep (WorldCell Cell) {
        if (Cell.getCellID() == Bugs.get(SelectedBug.getID()).getCurrentPosition()+1 ||
                Cell.getCellID() == Bugs.get(SelectedBug.getID()).getCurrentPosition()-1 ||
                Cell.getCellID() == Bugs.get(SelectedBug.getID()).getCurrentPosition()+10 ||
                Cell.getCellID() == Bugs.get(SelectedBug.getID()).getCurrentPosition()-10)  {
            return true;
        }
        return false;
    }

    public void SelectPossibleSteps (Bug bug) {

        if (bug.getCurrentPosition() < 99) {
            if (bug.getCurrentPosition()%10 !=9 ) {
                WorldMap.get(bug.getCurrentPosition() + 1).Sprite.setColor(Color.PINK);
                WorldMap.get(bug.getCurrentPosition() + 1).Sprite.setScale(1.1f);
            }
        }
        if (bug.getCurrentPosition() > 0) {
            if (bug.getCurrentPosition() % 10 != 0) {
                WorldMap.get(bug.getCurrentPosition() - 1).Sprite.setColor(Color.PINK);
                WorldMap.get(bug.getCurrentPosition() - 1).Sprite.setScale(1.1f);
            }
        }
        if (bug.getCurrentPosition() <= 89) {
            WorldMap.get(bug.getCurrentPosition() + 10).Sprite.setColor(Color.PINK);
            WorldMap.get(bug.getCurrentPosition() + 10).Sprite.setScale(1.1f);
        }
        if (bug.getCurrentPosition() >=10) {
            WorldMap.get(bug.getCurrentPosition() - 10).Sprite.setColor(Color.PINK);
            WorldMap.get(bug.getCurrentPosition() - 10).Sprite.setScale(1.1f);
        }

    }

    public void UnSelectPossibleSteps (Bug bug) {

        if (bug.getCurrentPosition() < 99) {
            if (bug.getCurrentPosition()%10 !=9 ) {
                WorldMap.get(bug.getCurrentPosition() + 1).Sprite.setColor(Color.WHITE);
                WorldMap.get(bug.getCurrentPosition() + 1).Sprite.setScale(1.0f);
            }
        }
        if (bug.getCurrentPosition() > 0) {
            if (bug.getCurrentPosition()%10 !=0 ) {
                WorldMap.get(bug.getCurrentPosition() - 1).Sprite.setColor(Color.WHITE);
                WorldMap.get(bug.getCurrentPosition() - 1).Sprite.setScale(1.0f);
            }
        }
        if (bug.getCurrentPosition() <= 89) {
            WorldMap.get(bug.getCurrentPosition() + 10).Sprite.setColor(Color.WHITE);
            WorldMap.get(bug.getCurrentPosition() + 10).Sprite.setScale(1.0f);
        }
        if (bug.getCurrentPosition() >= 10) {
            WorldMap.get(bug.getCurrentPosition() - 10).Sprite.setColor(Color.WHITE);
            WorldMap.get(bug.getCurrentPosition() - 10).Sprite.setScale(1.0f);
        }

    }
}