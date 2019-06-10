/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCutSeqGroupDetailComponent } from 'app/entities/m-cut-seq-group/m-cut-seq-group-detail.component';
import { MCutSeqGroup } from 'app/shared/model/m-cut-seq-group.model';

describe('Component Tests', () => {
  describe('MCutSeqGroup Management Detail Component', () => {
    let comp: MCutSeqGroupDetailComponent;
    let fixture: ComponentFixture<MCutSeqGroupDetailComponent>;
    const route = ({ data: of({ mCutSeqGroup: new MCutSeqGroup(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCutSeqGroupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MCutSeqGroupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MCutSeqGroupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mCutSeqGroup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
