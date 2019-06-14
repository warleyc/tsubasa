/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MLeagueDetailComponent } from 'app/entities/m-league/m-league-detail.component';
import { MLeague } from 'app/shared/model/m-league.model';

describe('Component Tests', () => {
  describe('MLeague Management Detail Component', () => {
    let comp: MLeagueDetailComponent;
    let fixture: ComponentFixture<MLeagueDetailComponent>;
    const route = ({ data: of({ mLeague: new MLeague(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLeagueDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MLeagueDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MLeagueDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mLeague).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
