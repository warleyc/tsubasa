/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTargetNationalityGroupDetailComponent } from 'app/entities/m-target-nationality-group/m-target-nationality-group-detail.component';
import { MTargetNationalityGroup } from 'app/shared/model/m-target-nationality-group.model';

describe('Component Tests', () => {
  describe('MTargetNationalityGroup Management Detail Component', () => {
    let comp: MTargetNationalityGroupDetailComponent;
    let fixture: ComponentFixture<MTargetNationalityGroupDetailComponent>;
    const route = ({ data: of({ mTargetNationalityGroup: new MTargetNationalityGroup(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTargetNationalityGroupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MTargetNationalityGroupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTargetNationalityGroupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mTargetNationalityGroup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
