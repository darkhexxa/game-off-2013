package com.me.corruption.entities;

import java.util.HashSet;

import com.badlogic.gdx.math.GridPoint2;
import com.me.corruption.hexMap.HexMap;
import com.me.corruption.hexMap.HexMap.Cell;

public class PlayerEntity extends Entity {

	private HashSet<Cell> visible = new HashSet<Cell>();

	
	public PlayerEntity( HexMap map) {
		super(map,"player");
	}
	
	public HashSet<Cell> getVisible() {
		return visible;
	}
	
	@Override
	public void addOwnedCell(Cell cell)
	{
		super.addOwnedCell(cell);
		this.updateVisible(cell);
	}
	
	@Override
	public void removeCell(Cell cell) {
		super.removeCell(cell);
		repocessVisible();
	}
	
	private void setCellAsVisible(Cell cell) {
		if(cell != null && cell.owner instanceof NeutralEntity) {
			visible.add(cell);
		}
	}	
	
	private void updateVisible(Cell cell) {
		final GridPoint2 point = cell.point;
		
		if( point.x % 2 == 0) {
			setCellAsVisible(map.getCell(point.x-1, point.y-1));
			setCellAsVisible(map.getCell(point.x-0, point.y-1));
			setCellAsVisible(map.getCell(point.x+1, point.y-1));
			setCellAsVisible(map.getCell(point.x-1, point.y+0));
			setCellAsVisible(map.getCell(point.x+1, point.y+0));
			setCellAsVisible(map.getCell(point.x-0, point.y+1));
		}
		else {
			
			setCellAsVisible(map.getCell(point.x-1, point.y+1));
			setCellAsVisible(map.getCell(point.x-0, point.y+1));
			setCellAsVisible(map.getCell(point.x+1, point.y+1));
			setCellAsVisible(map.getCell(point.x-1, point.y+0));
			setCellAsVisible(map.getCell(point.x+1, point.y+0));
			setCellAsVisible(map.getCell(point.x-0, point.y-1));
		}
	}
	
	private void repocessVisible() {
		visible.clear();
		for(Cell c : getOwnedCells()) {
			updateVisible(c);
		}
	}
	

	@Override
	public void tick(float dt) {
		// TODO Auto-generated method stub
		
	}
}
