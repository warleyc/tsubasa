/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTargetFormationGroupDetailComponent } from 'app/entities/m-target-formation-group/m-target-formation-group-detail.component';
import { MTargetFormationGroup } from 'app/shared/model/m-target-formation-group.model';

describe('Component Tests', () => {
  describe('MTargetFormationGroup Management Detail Component', () => {
    let comp: MTargetFormationGroupDetailComponent;
    let fixture: ComponentFixture<MTargetFormationGroupDetailComponent>;
    const route = ({ data: of({ mTargetFormationGroup: new MTargetFormationGroup(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTargetFormationGroupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MTargetFormationGroupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTargetFormationGroupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mTargetFormationGroup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
