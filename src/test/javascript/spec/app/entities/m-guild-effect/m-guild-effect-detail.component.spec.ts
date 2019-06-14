/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGuildEffectDetailComponent } from 'app/entities/m-guild-effect/m-guild-effect-detail.component';
import { MGuildEffect } from 'app/shared/model/m-guild-effect.model';

describe('Component Tests', () => {
  describe('MGuildEffect Management Detail Component', () => {
    let comp: MGuildEffectDetailComponent;
    let fixture: ComponentFixture<MGuildEffectDetailComponent>;
    const route = ({ data: of({ mGuildEffect: new MGuildEffect(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuildEffectDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MGuildEffectDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGuildEffectDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mGuildEffect).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
