<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Fichefinance
 *
 * @ORM\Table(name="fichefinance", indexes={@ORM\Index(name="idUtilisateur", columns={"idUtilisateur"})})
 * @ORM\Entity
 */
class Fichefinance
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="bigint", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_creation", type="date", nullable=false)
     */
    private $dateCreation;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=255, nullable=false)
     */
    private $description;

    /**
     * @var float
     *
     * @ORM\Column(name="total", type="float", precision=10, scale=0, nullable=false)
     */
    private $total;

    /**
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idUtilisateur", referencedColumnName="ID_User")
     * })
     */
    private $idutilisateur;

    public function getId(): ?string
    {
        return $this->id;
    }

    public function getDateCreation(): ?\DateTimeInterface
    {
        return $this->dateCreation;
    }

    public function setDateCreation(\DateTimeInterface $dateCreation): self
    {
        $this->dateCreation = $dateCreation;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getTotal(): ?float
    {
        return $this->total;
    }

    public function setTotal(float $total): self
    {
        $this->total = $total;

        return $this;
    }

    public function getIdutilisateur(): ?User
    {
        return $this->idutilisateur;
    }

    public function setIdutilisateur(?User $idutilisateur): self
    {
        $this->idutilisateur = $idutilisateur;

        return $this;
    }

    public function __toString()
    {
        return $this->getId();
    }


}
