package com.sea.cache.process;

import java.io.Serializable;
import java.util.List;

import com.sea.content.Svc;
import com.sea.db.bean.Player_armys;
import com.sea.db.bean.Player_build_obst;
import com.sea.db.bean.Player_buildings;
import com.sea.db.bean.Player_hero;
import com.sea.db.bean.Player_producting;
import com.sea.db.bean.Player_tech;

/**
 * 玩家信息实体(建筑，科技，障碍，英雄，拥有兵，生产兵)
 * 
 * @author Administrator
 * 
 */
public class PInfoAll implements Serializable {

	private static final long serialVersionUID = 1L;
	private int pcid;
	private boolean isLoadBuild;
	private boolean isLoadObsts;
	private boolean isLoadTechs;
	private boolean isLoadHeros;
	private boolean isLoadOwnArmys;
	private boolean isLoadProducts;

	private List<Player_buildings> builds;
	private List<Player_build_obst> obsts;
	private List<Player_tech> teches;
	private List<Player_hero> heroes;
	private List<Player_armys> ownArmys;
	private List<Player_producting> products;

	public int getPcid() {
		return pcid;
	}

	public void setPcid(int pcid) {
		this.pcid = pcid;
	}

	public List<Player_buildings> getBuilds() {
		return builds;
	}

	public void setBuilds(List<Player_buildings> builds) {
		this.builds = builds;
	}

	public List<Player_build_obst> getObsts() {
		return obsts;
	}

	public void setObsts(List<Player_build_obst> obsts) {
		this.obsts = obsts;
	}

	public List<Player_tech> getTeches() {
		return teches;
	}

	public void setTeches(List<Player_tech> teches) {
		this.teches = teches;
	}

	public List<Player_hero> getHeroes() {
		return heroes;
	}

	public void setHeroes(List<Player_hero> heroes) {
		this.heroes = heroes;
	}

	public List<Player_armys> getOwnArmys() {
		return ownArmys;
	}

	public void setOwnArmys(List<Player_armys> ownArmys) {
		this.ownArmys = ownArmys;
	}

	public List<Player_producting> getProducts() {
		return products;
	}

	public void setProducts(List<Player_producting> products) {
		this.products = products;
	}

	public boolean isLoadBuild() {
		if (isLoadBuild) {
			isLoadBuild = !Svc.isEmpty(this.getBuilds())
					&& this.getBuilds().size() >= 6;
		}
		return isLoadBuild;
	}

	public void setLoadBuild(boolean isLoadBuild) {
		this.isLoadBuild = isLoadBuild;
	}

	public boolean isLoadObsts() {
		if (isLoadObsts) {
			isLoadObsts = !Svc.isEmpty(this.getObsts());
		}
		return isLoadObsts;
	}

	public void setLoadObsts(boolean isLoadObsts) {
		this.isLoadObsts = isLoadObsts;
	}

	public boolean isLoadTechs() {
		return isLoadTechs;
	}

	public void setLoadTechs(boolean isLoadTechs) {
		this.isLoadTechs = isLoadTechs;
	}

	public boolean isLoadOwnArmys() {
		return isLoadOwnArmys;
	}

	public void setLoadOwnArmys(boolean isLoadOwnArmys) {
		this.isLoadOwnArmys = isLoadOwnArmys;
	}

	public boolean isLoadProducts() {
		return isLoadProducts;
	}

	public void setLoadProducts(boolean isLoadProducts) {
		this.isLoadProducts = isLoadProducts;
	}

	public boolean isLoadHeros() {
		return isLoadHeros;
	}

	public void setLoadHeros(boolean isLoadHeros) {
		this.isLoadHeros = isLoadHeros;
	}

	public PInfoAll() {
		super();
	}

	public PInfoAll(int pcid, List<Player_buildings> builds,
			List<Player_build_obst> obsts, List<Player_tech> teches,
			List<Player_hero> heroes, List<Player_armys> ownArmys,
			List<Player_producting> products) {
		this.pcid = pcid;
		this.builds = builds;
		this.obsts = obsts;
		this.teches = teches;
		this.heroes = heroes;
		this.ownArmys = ownArmys;
		this.products = products;
	}

	public void reload(boolean isLoadBuild, boolean isLoadObsts,
			boolean isLoadTechs, boolean isLoadOwnArmys,
			boolean isLoadProducts, boolean isLoadHeros) {
		this.isLoadBuild = isLoadBuild;
		this.isLoadObsts = isLoadObsts;
		this.isLoadTechs = isLoadTechs;
		this.isLoadOwnArmys = isLoadOwnArmys;
		this.isLoadProducts = isLoadProducts;
		this.isLoadHeros = isLoadHeros;
	}

	public boolean isLoad() {
		return this.isLoadBuild() && this.isLoadHeros && this.isLoadObsts()
				&& this.isLoadOwnArmys && this.isLoadProducts
				&& this.isLoadTechs;
	}

	public void reset() {
		this.isLoadBuild = false;
		this.isLoadObsts = false;
		this.isLoadTechs = false;
		this.isLoadOwnArmys = false;
		this.isLoadProducts = false;
		this.isLoadHeros = false;

		if (this.builds != null)
			this.builds.clear();

		if (this.obsts != null)
			this.obsts.clear();

		if (this.ownArmys != null)
			this.ownArmys.clear();

		if (this.heroes != null)
			this.heroes.clear();

		if (this.products != null)
			this.products.clear();

		if (this.teches != null)
			this.teches.clear();
	}
}