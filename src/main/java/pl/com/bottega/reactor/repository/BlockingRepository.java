package pl.com.bottega.reactor.repository;

public interface BlockingRepository<T> {

	void save(T value);

	T findFirst();

	Iterable<T> findAll();

	T findById(String id);
}
