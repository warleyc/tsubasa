/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTargetActionGroupDetailComponent } from 'app/entities/m-target-action-group/m-target-action-group-detail.component';
import { MTargetActionGroup } from 'app/shared/model/m-target-action-group.model';

describe('Component Tests', () => {
  describe('MTargetActionGroup Management Detail Component', () => {
    let comp: MTargetActionGroupDetailComponent;
    let fixture: ComponentFixture<MTargetActionGroupDetailComponent>;
    const route = ({ data: of({ mTargetActionGroup: new MTargetActionGroup(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTargetActionGroupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MTargetActionGroupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTargetActionGroupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mTargetActionGroup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
