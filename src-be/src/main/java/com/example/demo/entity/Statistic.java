package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateStart;

    private String teams;

    @ManyToOne
    @JoinColumn(name = "tournament_name", referencedColumnName = "name")
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "sport_type", referencedColumnName = "type")
    private SportType sportType;

    private String link;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Statistic statistic)) {
            return false;
        }

        if (!Objects.equals(id, statistic.id)) {
            return false;
        }
        if (!Objects.equals(dateStart, statistic.dateStart)) {
            return false;
        }
        if (!Objects.equals(teams, statistic.teams)) {
            return false;
        }
        if (!Objects.equals(tournament, statistic.tournament)) {
            return false;
        }
        if (!Objects.equals(sportType, statistic.sportType)) {
            return false;
        }
        return Objects.equals(link, statistic.link);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (teams != null ? teams.hashCode() : 0);
        result = 31 * result + (tournament != null ? tournament.hashCode() : 0);
        result = 31 * result + (sportType != null ? sportType.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        return result;
    }
}
