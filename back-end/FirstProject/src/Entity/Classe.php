<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Classe
 *
 * @ORM\Table(name="classe")
 * @ORM\Entity
 */
class Classe
{
    /**
     * @var int
     *
     * @ORM\Column(name="ID_Class", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idClass;

    /**
     * @var string
     *
     * @ORM\Column(name="ClassName", type="string", length=6, nullable=false)
     */
    private $classname;

    /**
     * @var int
     *
     * @ORM\Column(name="Capacity", type="integer", nullable=false)
     */
    private $capacity;

    /**
     * @var int
     *
     * @ORM\Column(name="Level", type="integer", nullable=false)
     */
    private $level;

    public function getIdClass(): ?int
    {
        return $this->idClass;
    }

    public function getClassname(): ?string
    {
        return $this->classname;
    }

    public function setClassname(string $classname): self
    {
        $this->classname = $classname;

        return $this;
    }

    public function getCapacity(): ?int
    {
        return $this->capacity;
    }

    public function setCapacity(int $capacity): self
    {
        $this->capacity = $capacity;

        return $this;
    }

    public function getLevel(): ?int
    {
        return $this->level;
    }

    public function setLevel(int $level): self
    {
        $this->level = $level;

        return $this;
    }


}
