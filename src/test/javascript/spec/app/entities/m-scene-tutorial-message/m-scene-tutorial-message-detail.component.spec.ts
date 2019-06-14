/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MSceneTutorialMessageDetailComponent } from 'app/entities/m-scene-tutorial-message/m-scene-tutorial-message-detail.component';
import { MSceneTutorialMessage } from 'app/shared/model/m-scene-tutorial-message.model';

describe('Component Tests', () => {
  describe('MSceneTutorialMessage Management Detail Component', () => {
    let comp: MSceneTutorialMessageDetailComponent;
    let fixture: ComponentFixture<MSceneTutorialMessageDetailComponent>;
    const route = ({ data: of({ mSceneTutorialMessage: new MSceneTutorialMessage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MSceneTutorialMessageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MSceneTutorialMessageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MSceneTutorialMessageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mSceneTutorialMessage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
