package com.codetutr.form;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriberSimple {

	private String name;
	private String email;
	private Integer age;
	private Gender gender;
	private Frequency newsletterFrequency;
	private Boolean receiveNewsletter;

	public SubscriberSimple() {
	}

	public SubscriberSimple(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SubscriberSimple [name=" + name + ", age=" + age + ", gender=" + gender
				+ ", newsletterFrequency=" + newsletterFrequency
				+ ", receiveNewsletter=" + receiveNewsletter + "]";
	}

	public static void main(String[] args) throws InterruptedException {

		class KK implements Comparable {
			public int key;

			public KK(int key) {
				this.key = key;
			}

			@Override
			public String toString() {
				return "kk"+key;
			}

			@Override
			public int compareTo(Object o) {
				int other = ((KK) o).key;
				return (key > other ? 1 : (key == other ? 0 : -1));
				// но ошибка этого compareTo в том, что по умолчанию (если их не переопределять)
				// equals() вернёт что объекты разные
				// и хэш коды для них тоже будут разные
			}

		}

		TreeMap<String, SubscriberSimple> mapString = new TreeMap<>();
		mapString.put("s4", new SubscriberSimple("Boroda"));
		mapString.put("s1", new SubscriberSimple("name"));
		mapString.put("s3", new SubscriberSimple("MOrta"));
		mapString.put("s2", new SubscriberSimple("Oleg"));
		mapString.forEach((k, v) -> System.out.println(k + " :: " + v.getName())); // need lombok plugin

		Thread.sleep(500);

		//Exception in thread "main" java.lang.ClassCastException:
		// com.codetutr.form.SubscriberSimple$1KK cannot be cast to java.lang.Comparable
		Map<KK, SubscriberSimple> map =
				new TreeMap<>();
//				new HashMap<>();

		map.put(new KK(11), new SubscriberSimple("Boroda"));
		map.put(new KK(33), new SubscriberSimple("name"));
		map.put(new KK(44), new SubscriberSimple("MArta"));
		map.put(new KK(22), new SubscriberSimple("Oleg"));
		KK key551 = new KK(55);
		KK key552 = new KK(55);
		map.put(key551, new SubscriberSimple("AA_55"));
		map.put(key552, new SubscriberSimple("BB_55")); // заменит AA_55
		map.forEach((k, v) -> System.out.println(k + " :: " + v.getName()));

		System.out.println("k551="+key551.hashCode());
		System.out.println("k552="+key552.hashCode());
		System.out.println("equals="+key551.equals(key552));

		class FF {
			public final int f;
//			synchronized { // нельзя сделать блок синхронизированным
			{
				f = 1;
			}

			public FF() {
				System.out.println("f1 = " + f);
//				f=2; // нельзя, т.к. уже заасанена в блоке.
			}
		}
		FF fin = new FF();
		System.out.println("f2 = " + fin.f);
	} // main
}
