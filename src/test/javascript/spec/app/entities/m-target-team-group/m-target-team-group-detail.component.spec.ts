/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTargetTeamGroupDetailComponent } from 'app/entities/m-target-team-group/m-target-team-group-detail.component';
import { MTargetTeamGroup } from 'app/shared/model/m-target-team-group.model';

describe('Component Tests', () => {
  describe('MTargetTeamGroup Management Detail Component', () => {
    let comp: MTargetTeamGroupDetailComponent;
    let fixture: ComponentFixture<MTargetTeamGroupDetailComponent>;
    const route = ({ data: of({ mTargetTeamGroup: new MTargetTeamGroup(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTargetTeamGroupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MTargetTeamGroupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTargetTeamGroupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mTargetTeamGroup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
