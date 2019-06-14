/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTargetActionTypeGroupDetailComponent } from 'app/entities/m-target-action-type-group/m-target-action-type-group-detail.component';
import { MTargetActionTypeGroup } from 'app/shared/model/m-target-action-type-group.model';

describe('Component Tests', () => {
  describe('MTargetActionTypeGroup Management Detail Component', () => {
    let comp: MTargetActionTypeGroupDetailComponent;
    let fixture: ComponentFixture<MTargetActionTypeGroupDetailComponent>;
    const route = ({ data: of({ mTargetActionTypeGroup: new MTargetActionTypeGroup(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTargetActionTypeGroupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MTargetActionTypeGroupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTargetActionTypeGroupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mTargetActionTypeGroup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
