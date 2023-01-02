package com.iprwc.webshop.dao;

import com.iprwc.webshop.model.Car;
import com.iprwc.webshop.model.Role;
import com.iprwc.webshop.repositories.CarRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class CarDAO {

    private final CarRepository carRepository;

    public CarDAO(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public ArrayList<Car> index() {
        return (ArrayList<Car>) carRepository.findAll();
    }

    /**
     * Returns a product object with a specific id.
     * If there is no product with the specified id, returns null
     *
     * @param id the id of the product
     * @return product
     */
    public Car show(Integer id) {
        Optional<Car> car = this.carRepository.findById(id);

        return car.orElse(null);
    }

    public Car store(Car car) {
        return carRepository.save(car);
    }

    public boolean update(Car car) {
        try {
            carRepository.update(car.getTitle(), car.getDescription(), car.getCategory(), car.getManufacturer(), car.getEngine_displacement(), car.getCylinders(), car.getYear(), car.getKm(), car.getThumbnailUri(), car.getId());

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(Car car) {
        try {
            carRepository.delete(car);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}