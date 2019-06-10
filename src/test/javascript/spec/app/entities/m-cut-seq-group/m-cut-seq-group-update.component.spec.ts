/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCutSeqGroupUpdateComponent } from 'app/entities/m-cut-seq-group/m-cut-seq-group-update.component';
import { MCutSeqGroupService } from 'app/entities/m-cut-seq-group/m-cut-seq-group.service';
import { MCutSeqGroup } from 'app/shared/model/m-cut-seq-group.model';

describe('Component Tests', () => {
  describe('MCutSeqGroup Management Update Component', () => {
    let comp: MCutSeqGroupUpdateComponent;
    let fixture: ComponentFixture<MCutSeqGroupUpdateComponent>;
    let service: MCutSeqGroupService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCutSeqGroupUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MCutSeqGroupUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MCutSeqGroupUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MCutSeqGroupService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MCutSeqGroup(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MCutSeqGroup();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
