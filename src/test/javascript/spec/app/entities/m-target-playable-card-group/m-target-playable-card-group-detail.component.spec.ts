/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTargetPlayableCardGroupDetailComponent } from 'app/entities/m-target-playable-card-group/m-target-playable-card-group-detail.component';
import { MTargetPlayableCardGroup } from 'app/shared/model/m-target-playable-card-group.model';

describe('Component Tests', () => {
  describe('MTargetPlayableCardGroup Management Detail Component', () => {
    let comp: MTargetPlayableCardGroupDetailComponent;
    let fixture: ComponentFixture<MTargetPlayableCardGroupDetailComponent>;
    const route = ({ data: of({ mTargetPlayableCardGroup: new MTargetPlayableCardGroup(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTargetPlayableCardGroupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MTargetPlayableCardGroupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTargetPlayableCardGroupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mTargetPlayableCardGroup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
