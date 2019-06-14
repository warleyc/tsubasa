/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTeamDetailComponent } from 'app/entities/m-team/m-team-detail.component';
import { MTeam } from 'app/shared/model/m-team.model';

describe('Component Tests', () => {
  describe('MTeam Management Detail Component', () => {
    let comp: MTeamDetailComponent;
    let fixture: ComponentFixture<MTeamDetailComponent>;
    const route = ({ data: of({ mTeam: new MTeam(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTeamDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MTeamDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTeamDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mTeam).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
