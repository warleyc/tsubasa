/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MLeagueRegulationDetailComponent } from 'app/entities/m-league-regulation/m-league-regulation-detail.component';
import { MLeagueRegulation } from 'app/shared/model/m-league-regulation.model';

describe('Component Tests', () => {
  describe('MLeagueRegulation Management Detail Component', () => {
    let comp: MLeagueRegulationDetailComponent;
    let fixture: ComponentFixture<MLeagueRegulationDetailComponent>;
    const route = ({ data: of({ mLeagueRegulation: new MLeagueRegulation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLeagueRegulationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MLeagueRegulationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MLeagueRegulationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mLeagueRegulation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
