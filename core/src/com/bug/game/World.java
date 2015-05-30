package com.bug.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import Screen.PlayScreen;

import static com.badlogic.gdx.Gdx.graphics;

public class World {

    static private int CellCount = 100;  // Количество ячееек в мире (на поле).
    // Масив ячеек мира (поля).
    public Map<Integer, WorldCell> WorldMap = new HashMap<Integer, WorldCell>();
    public ArrayList<Bug> Bugs = new ArrayList<Bug>();  // Масив жуков назодящихся в мире (на поле).
    public ArrayList<Buffs> Buff = new ArrayList<Buffs>();  // Масив баффов назодящихся в мире (на поле).
    public StatusBar StatusBar = new StatusBar();

    private ArrayList <Integer> ExistingID = new ArrayList<Integer>();
    private Bounds WorldBound;  // Размеры мира.
    public Bug SelectedBug;
    float minSide;
    String minSideCell;
    float w = graphics.getWidth();
    float h = graphics.getHeight();
    public Sound StepSound = Gdx.audio.newSound(Gdx.files.internal("sounds/Steps_bug.wav"));

    World() {
        // SetWorldBounds(); // Установка размера игрового поля. Зависимость от размеров экрана.
        GenCells(CellCount);  // Генерация ячеек игрового поля.
        GenBugs(1, 7);
        GenBuffs(3);
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
        int CellEndPosition = 0;

        if (minSideCell.equals("x")) {
            float startx = step;
            float starty = 0;//(graphics.getWidth() / 2) - (cell * 5) - (0 * 4);
            for (int i = 0; i < CellCount; i++) {
                 // Расчет позиции текущей ячейки (в будущем)
                WorldMap.put(i, GenCell( i, new Bounds(startx, starty, cell)));
                startx += cell;
                if (i % 10 == 9 && i != 0) {
                    starty += cell;
                    startx = step;
                }
            }
            CellEndPosition = (int)starty;

        } else if (minSideCell.equals("y")) {
            float starty = 0 + step;
            float startx =  0; //(graphics.getWidth() / 2) - (cell * 5) - (0 * 4);
            for (int i = 0; i < CellCount; i++) {
                Bounds bound = new Bounds(startx, starty, cell); // Расчет позиции текущей ячейки (в будущем)
                WorldMap.put(i, GenCell(i, bound));
                starty += cell;
                if (i % 10 == 9 && i != 0) {
                    startx += cell;
                    starty = step ;
                }
            }
            CellEndPosition = (int)startx;
        }
        StatusBar.BuildStatusBar((int)CellEndPosition,0,(int)(graphics.getWidth()-CellEndPosition),graphics.getHeight());
    }

    private WorldCell GenCell(String Name, int CellID, Bounds CellBound) {
        return new WorldCell(Name, CellID, CellBound);
    }

    private WorldCell GenCell( int CellID, Bounds CellBound) {
        return new WorldCell(null, CellID, CellBound);
    }

    private void GenBugs(String Name, int StartCellID) {

        Bugs.add(new Bug(Name, StartCellID, getCell(StartCellID).GetCellCords(), Bugs.size()));

    }

    private void GenBugs(int BugsNum, int TeamNum) {
        String [] TeamsColorPattern = {"red","blue","orange","yellow","green","indigo","violet"};



        for (int Team=0; Team<TeamNum; Team++) {
            for (int Bug = 0; Bug < BugsNum; Bug++) {
                int StartCellID = GenCellID(ExistingID);
                ExistingID.add(StartCellID);
                Bugs.add(new Bug(TeamsColorPattern[Team], StartCellID, getCell(StartCellID).GetCellCords(), Bugs.size()));
                WorldMap.get(StartCellID).setName(TeamsColorPattern[Team]);
            }

        }
    }

    private void GenBuffs(int Num) {
        String [] BuffPattern = {"heal","damage","range"};

            for (int count = 0; count < Num; count++) {
                int StartCellID = GenCellID(ExistingID);
                ExistingID.add(StartCellID);
                Buff.add(new Buffs(BuffPattern[new Random().nextInt(Num)],StartCellID, getCell(StartCellID).GetCellCords()));
                WorldMap.get(StartCellID).buff = Buff.get(count);
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




    public void MakeStep (WorldCell Cell, Stage stage) {

        if (Cell.getCellID() == Bugs.get(SelectedBug.getID()).getCurrentPosition()+1 ||
            Cell.getCellID() == Bugs.get(SelectedBug.getID()).getCurrentPosition()-1 ||
            Cell.getCellID() == Bugs.get(SelectedBug.getID()).getCurrentPosition()+10 ||
            Cell.getCellID() == Bugs.get(SelectedBug.getID()).getCurrentPosition()-10)
        {

            UnSelectPossibleSteps(SelectedBug);

            if(Cell.getCellID() == Bugs.get(SelectedBug.getID()).getCurrentPosition()-1) {
                Bugs.get(SelectedBug.getID()).Sprite.setOriginCenter();
                Bugs.get(SelectedBug.getID()).Sprite.setRotation(180);
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
            CheckBuff();
            long soundId = StepSound.play();
            StepSound.setVolume(soundId,((float) (0.8)));
           // ChekFields();
        }
    }
    public void CheckBuff(){

           for (Buffs Buff_buff:Buff){
               if (Buff_buff.CurrentPossition == SelectedBug.getCurrentPosition()){
                   Buff_buff.clear();
                   Buff_buff.ApplyBuff(SelectedBug);
               }

            }
    }



    public boolean CheckNextStep (WorldCell Cell) {
        if (    Cell.getCellID() == Bugs.get(SelectedBug.getID()).getCurrentPosition()+1 ||
                Cell.getCellID() == Bugs.get(SelectedBug.getID()).getCurrentPosition()-1 ||
                Cell.getCellID() == Bugs.get(SelectedBug.getID()).getCurrentPosition()+10 ||
                Cell.getCellID() == Bugs.get(SelectedBug.getID()).getCurrentPosition()-10)  {
            return true;
        }
        return false;
    }

    public void SelectPossibleSteps (Bug bug) {
        float Scale = 1.2f;
        if (bug.getCurrentPosition() < 99 && bug.getCurrentPosition()%10 !=9) {
                if (!CheckNearBugs(bug, 1)) {
                    WorldMap.get(bug.getCurrentPosition() + 1).Sprite.setColor(Color.PINK);
                    WorldMap.get(bug.getCurrentPosition() + 1).Sprite.setScale(Scale);
                }

    }

        if (bug.getCurrentPosition() > 0 && bug.getCurrentPosition() % 10 != 0) {
            if (!CheckNearBugs(bug, -1)) {
                WorldMap.get(bug.getCurrentPosition() - 1).Sprite.setColor(Color.PINK);
                WorldMap.get(bug.getCurrentPosition() - 1).Sprite.setScale(Scale);
            }
        }

        if (bug.getCurrentPosition() <= 89 ) {
            if (!CheckNearBugs(bug, 10)) {
                WorldMap.get(bug.getCurrentPosition() + 10).Sprite.setColor(Color.PINK);
                WorldMap.get(bug.getCurrentPosition() + 10).Sprite.setScale(Scale);
            }
        }
        if (bug.getCurrentPosition() >=10) {
            if (!CheckNearBugs(bug, -10)) {
                WorldMap.get(bug.getCurrentPosition() - 10).Sprite.setColor(Color.PINK);
                WorldMap.get(bug.getCurrentPosition() - 10).Sprite.setScale(Scale);
            }
        }

    }

    public void UnSelectPossibleSteps (Bug bug) {
        float Scale = 1.0f;
        if (bug.getCurrentPosition() < 99) {
            if (bug.getCurrentPosition()%10 !=9 ) {
                WorldMap.get(bug.getCurrentPosition() + 1).Sprite.setColor(Color.WHITE);
                WorldMap.get(bug.getCurrentPosition() + 1).Sprite.setScale(Scale);
            }
        }
        if (bug.getCurrentPosition() > 0) {
            if (bug.getCurrentPosition()%10 !=0 ) {
                WorldMap.get(bug.getCurrentPosition() - 1).Sprite.setColor(Color.WHITE);
                WorldMap.get(bug.getCurrentPosition() - 1).Sprite.setScale(Scale);
            }
        }
        if (bug.getCurrentPosition() <= 89) {
            WorldMap.get(bug.getCurrentPosition() + 10).Sprite.setColor(Color.WHITE);
            WorldMap.get(bug.getCurrentPosition() + 10).Sprite.setScale(Scale);
        }
        if (bug.getCurrentPosition() >= 10) {
            WorldMap.get(bug.getCurrentPosition() - 10).Sprite.setColor(Color.WHITE);
            WorldMap.get(bug.getCurrentPosition() - 10).Sprite.setScale(Scale);
        }

    }

    public boolean CheckNearBugs(Bug bug, int Way) {


        for (Bug BuffBug:Bugs) {
            if (bug.getCurrentPosition() + Way == BuffBug.getCurrentPosition()) {
                return true;
            }
        }
        return false;

    }

    // ЗАхват полигонов по идее игры Реверси

    private void ChekFields () {

        for (int key:WorldMap.keySet()) {
            System.out.println(WorldMap.get(key).getName());
            if (WorldMap.get(key).getName() != null && key%10 != 9) {
                if (WorldMap.get(key + 1).getName() != WorldMap.get(key).getName()) {

                    String s = String.valueOf(key);
                    System.out.println(s + " " + s.charAt(0));
                    int KeyLength;
                    if (s.length() <= 1) {
                        KeyLength = 9;
                    } else {
                        KeyLength = 9 + (Integer.valueOf(String.valueOf(s.charAt(0))) * 10);
                    }
                    System.out.println(KeyLength);
                    for (int i = key + 1; i <= KeyLength; i++) {

                        if (WorldMap.get(key).getName() == WorldMap.get(i).getName()) {
                            for (int j = key; j <= i; j++) {
                                WorldMap.get(j).setName(WorldMap.get(key).getName());
                            }
                        }
                    }

                }
            }
        }

    }
}