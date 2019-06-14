/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MSceneTutorialMessageUpdateComponent } from 'app/entities/m-scene-tutorial-message/m-scene-tutorial-message-update.component';
import { MSceneTutorialMessageService } from 'app/entities/m-scene-tutorial-message/m-scene-tutorial-message.service';
import { MSceneTutorialMessage } from 'app/shared/model/m-scene-tutorial-message.model';

describe('Component Tests', () => {
  describe('MSceneTutorialMessage Management Update Component', () => {
    let comp: MSceneTutorialMessageUpdateComponent;
    let fixture: ComponentFixture<MSceneTutorialMessageUpdateComponent>;
    let service: MSceneTutorialMessageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MSceneTutorialMessageUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MSceneTutorialMessageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MSceneTutorialMessageUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MSceneTutorialMessageService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MSceneTutorialMessage(123);
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
        const entity = new MSceneTutorialMessage();
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
