/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDefineUpdateComponent } from 'app/entities/m-define/m-define-update.component';
import { MDefineService } from 'app/entities/m-define/m-define.service';
import { MDefine } from 'app/shared/model/m-define.model';

describe('Component Tests', () => {
  describe('MDefine Management Update Component', () => {
    let comp: MDefineUpdateComponent;
    let fixture: ComponentFixture<MDefineUpdateComponent>;
    let service: MDefineService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDefineUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MDefineUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MDefineUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MDefineService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MDefine(123);
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
        const entity = new MDefine();
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
