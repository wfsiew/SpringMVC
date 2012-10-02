package springmvc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.log.Log;

import springmvc.*;
import springmvc.domain.Car;

@Service("carService")
@Transactional
public class CarService {
	protected static Logger logger = Logger.getLogger("CarService");

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Transactional(readOnly = true)
	public Map<String, Object> getAll(int pageNum, int pageSize) {
		logger.info("Retrieving all cars");

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM  Car");

		int total = getTotalCars(session);
		Pager pager = new Pager(total, pageNum, pageSize);

		String item_msg = pager.getItemMessage();

		int lowerbound = pager.getLowerBound();
		int hasnext = (pager.hasNext() ? 1 : 0);
		int hasprev = (pager.hasPrev() ? 1 : 0);

		Map<String, Object> dic = new HashMap<String, Object>();
		dic.put("item_msg", item_msg);
		dic.put("hasnext", hasnext);
		dic.put("hasprev", hasprev);
		dic.put("nextpage", pageNum + 1);
		dic.put("prevpage", pageNum - 1);

		query.setFirstResult(lowerbound);
		query.setMaxResults(pager.getPageSize());

		List<Car> cars = query.list();

		dic.put("list", cars);

		return dic;
	}

	@Transactional(readOnly = true)
	public Map<String, Object> getFilterBy(int find, String keyword,
			int pageNum, int pageSize) {
		logger.info("Retrieving cars filter by " + keyword);

		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Car.class);
		getFilterCriteria(criteria, find, keyword);

		int total = criteria.list().size();
		Pager pager = new Pager(total, pageNum, pageSize);

		String item_msg = pager.getItemMessage();

		int lowerbound = pager.getLowerBound();
		int hasnext = (pager.hasNext() ? 1 : 0);
		int hasprev = (pager.hasPrev() ? 1 : 0);

		Map<String, Object> dic = new HashMap<String, Object>();
		dic.put("item_msg", item_msg);
		dic.put("hasnext", hasnext);
		dic.put("hasprev", hasprev);
		dic.put("nextpage", pageNum + 1);
		dic.put("prevpage", pageNum - 1);

		criteria.setFirstResult(lowerbound);
		criteria.setMaxResults(pager.getPageSize());

		List<Car> cars = criteria.list();

		dic.put("list", cars);

		return dic;
	}

	@Transactional(readOnly = true)
	public Car get(int id) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Retrieve existing car first
		Car car = (Car) session.get(Car.class, id);

		return car;
	}

	public void add(Car car) {
		logger.info("Adding new car");

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Save
		session.save(car);
	}

	public void delete(int id) {
		logger.info("Deleting existing car");

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Retrieve existing car first
		Car car = (Car) session.get(Car.class, id);

		// Delete
		session.delete(car);
	}

	public void edit(Car car) {
		logger.info("Editing existing car");

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Retrieve existing car via id
		Car existingCar = (Car) session.get(Car.class, car.getId());

		// Assign updated values to this car
		existingCar.setMake(car.getMake());
		existingCar.setModel(car.getModel());
		existingCar.setYear(car.getYear());
		existingCar.setDoors(car.getDoors());
		existingCar.setColour(car.getColour());
		existingCar.setPrice(car.getPrice());

		// Save updates
		session.save(existingCar);
	}

	public String getItemMessage(int find, String keyword, int pageNum,
			int pageSize) {
		Session session = sessionFactory.getCurrentSession();
		int total = 0;
		String item_msg = "";

		if (find == 0 && Utils.isEmptyString(keyword)) {
			total = getTotalCars(session);
			Pager pager = new Pager(total, pageNum, pageSize);
			item_msg = pager.getItemMessage();
			return item_msg;
		}

		else {
			Criteria criteria = session.createCriteria(Car.class);
			getFilterCriteria(criteria, find, keyword);
			total = criteria.list().size();
			Pager pager = new Pager(total, pageNum, pageSize);
			item_msg = pager.getItemMessage();
			return item_msg;
		}
	}

	@Transactional(readOnly = true)
	private Integer getTotalCars(Session session) {
		String q = "SELECT COUNT(*) FROM car";
		Query query = session.createSQLQuery(q);
		List val = query.list();
		Integer count = Integer.parseInt(val.get(0).toString());
		return count;
	}

	private void getFilterCriteria(Criteria criteria, int find, String keyword) {
		String text = String.format("%%%s%%", keyword);

		// Search by make
		if (find == 1) {
			Criterion qmake = Restrictions.ilike("make", text,
					MatchMode.ANYWHERE);
			criteria.add(qmake);
		}

		// Search by model
		else if (find == 2) {
			Criterion qmodel = Restrictions.ilike("model", text,
					MatchMode.ANYWHERE);
			criteria.add(qmodel);
		}

		// Search by year
		else if (find == 3) {
			try {
				int year = Integer.parseInt(keyword);
				Criterion qyear = Restrictions.eq("year", year);
				criteria.add(qyear);
			}

			catch (Exception e) {
			}
		}

		// Search by doors
		else if (find == 4) {
			try {
				int doors = Integer.parseInt(keyword);
				Criterion qdoors = Restrictions.eq("doors", doors);
				criteria.add(qdoors);
			}

			catch (Exception e) {
			}
		}

		// Search by colour
		else if (find == 5) {
			Criterion qcolour = Restrictions.ilike("colour", text,
					MatchMode.ANYWHERE);
			criteria.add(qcolour);
		}

		// Search by price
		else if (find == 6) {
			try {
				double price = Double.parseDouble(keyword);
				Criterion qprice = Restrictions.eq("price", price);
				criteria.add(qprice);
			}

			catch (Exception e) {
			}
		}

		// Search all
		else {
			boolean isNumeric = Utils.isNumber(keyword);

			if (!isNumeric) {
				Criterion qmake = Restrictions.ilike("make", text,
						MatchMode.ANYWHERE);
				Criterion qmodel = Restrictions.ilike("model", text,
						MatchMode.ANYWHERE);
				Criterion qcolour = Restrictions.ilike("colour", text,
						MatchMode.ANYWHERE);

				LogicalExpression exp1 = Restrictions.or(qmake, qmodel);
				LogicalExpression exp2 = Restrictions.or(exp1, qcolour);

				criteria.add(exp2);
			}

			else {
				int year = Utils.getInt(keyword);
				int doors = Utils.getInt(keyword);
				double price = Utils.getDouble(keyword);

				Criterion qyear = Restrictions.eq("year", year);
				Criterion qdoors = Restrictions.eq("doors", doors);
				Criterion qprice = Restrictions.eq("price", price);

				Criterion qmake = Restrictions.ilike("make", text,
						MatchMode.ANYWHERE);
				Criterion qmodel = Restrictions.ilike("model", text,
						MatchMode.ANYWHERE);
				Criterion qcolour = Restrictions.ilike("colour", text,
						MatchMode.ANYWHERE);

				LogicalExpression exp1 = Restrictions.or(qyear, qdoors);
				LogicalExpression exp2 = Restrictions.or(exp1, qprice);
				LogicalExpression exp3 = Restrictions.or(exp2, qmake);
				LogicalExpression exp4 = Restrictions.or(exp3, qmodel);
				LogicalExpression exp5 = Restrictions.or(exp4, qcolour);

				criteria.add(exp5);
			}
		}
	}
}
