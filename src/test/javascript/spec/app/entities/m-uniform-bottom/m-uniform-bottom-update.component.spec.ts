/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MUniformBottomUpdateComponent } from 'app/entities/m-uniform-bottom/m-uniform-bottom-update.component';
import { MUniformBottomService } from 'app/entities/m-uniform-bottom/m-uniform-bottom.service';
import { MUniformBottom } from 'app/shared/model/m-uniform-bottom.model';

describe('Component Tests', () => {
  describe('MUniformBottom Management Update Component', () => {
    let comp: MUniformBottomUpdateComponent;
    let fixture: ComponentFixture<MUniformBottomUpdateComponent>;
    let service: MUniformBottomService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MUniformBottomUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MUniformBottomUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MUniformBottomUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MUniformBottomService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MUniformBottom(123);
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
        const entity = new MUniformBottom();
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
