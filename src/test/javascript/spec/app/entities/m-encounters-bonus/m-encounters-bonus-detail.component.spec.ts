/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MEncountersBonusDetailComponent } from 'app/entities/m-encounters-bonus/m-encounters-bonus-detail.component';
import { MEncountersBonus } from 'app/shared/model/m-encounters-bonus.model';

describe('Component Tests', () => {
  describe('MEncountersBonus Management Detail Component', () => {
    let comp: MEncountersBonusDetailComponent;
    let fixture: ComponentFixture<MEncountersBonusDetailComponent>;
    const route = ({ data: of({ mEncountersBonus: new MEncountersBonus(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MEncountersBonusDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MEncountersBonusDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MEncountersBonusDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mEncountersBonus).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
