/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMarathonLoopRewardGroupDetailComponent } from 'app/entities/m-marathon-loop-reward-group/m-marathon-loop-reward-group-detail.component';
import { MMarathonLoopRewardGroup } from 'app/shared/model/m-marathon-loop-reward-group.model';

describe('Component Tests', () => {
  describe('MMarathonLoopRewardGroup Management Detail Component', () => {
    let comp: MMarathonLoopRewardGroupDetailComponent;
    let fixture: ComponentFixture<MMarathonLoopRewardGroupDetailComponent>;
    const route = ({ data: of({ mMarathonLoopRewardGroup: new MMarathonLoopRewardGroup(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMarathonLoopRewardGroupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MMarathonLoopRewardGroupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMarathonLoopRewardGroupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mMarathonLoopRewardGroup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
