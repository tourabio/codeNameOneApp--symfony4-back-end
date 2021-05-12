<?php

namespace App\Form;

use App\Entity\Recommendation;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class RecommendationType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('sujet' , TextType::class , array('attr' => array('class'=>'form-control ')))
            ->add('comment' , TextType::class , array('attr' => array('class'=>'form-control ')))
            ->add('date' , DateType::class , array('attr' => array('class'=>'form-control ')))
            ->add('etat' , TextType::class , array('attr' => array('class'=>'form-control ')))

        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Recommendation::class,
        ]);
    }
}
