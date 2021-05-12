<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Matiere
 *
 * @ORM\Table(name="matiere")
 * @ORM\Entity
 */
class Matiere
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_matiere", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idMatiere;

    /**
     * @var string
     *
     * @ORM\Column(name="nom_matiere", type="string", length=50, nullable=false)
     */
    private $nomMatiere;

    public function getIdMatiere(): ?int
    {
        return $this->idMatiere;
    }

    public function getNomMatiere(): ?string
    {
        return $this->nomMatiere;
    }

    public function setNomMatiere(string $nomMatiere): self
    {
        $this->nomMatiere = $nomMatiere;

        return $this;
    }


}
