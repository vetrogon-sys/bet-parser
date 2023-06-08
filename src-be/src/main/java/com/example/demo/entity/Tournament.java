package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tournament {

    @Id
    private String name;

    @ManyToOne
    @JoinColumn(name = "sport_type", referencedColumnName = "type")
    private SportType sportType;

    @OneToMany(mappedBy = "tournament")
    private List<Statistic> statistics;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tournament that)) {
            return false;
        }

        if (!Objects.equals(name, that.name)) {
            return false;
        }
        return Objects.equals(sportType, that.sportType);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (sportType != null ? sportType.hashCode() : 0);
        return result;
    }
}
